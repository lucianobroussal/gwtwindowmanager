/*
 * Copyright (c) 2006/2007 Flipperwing Ltd. (http://www.flipperwing.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwm.splice.client.service;

import java.util.Iterator;

import org.gwm.splice.client.desktop.DesktopManager;
import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.RemoteDataObject;
import org.gwm.splice.client.service.data.RemoteStatus;
import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.data.RemoteObjectFactory;
import org.gwm.splice.client.service.data.ValidationError;
import org.gwm.splice.client.service.data.Attributes.Attribute;
import org.gwm.splice.client.service.json.JSONSerializer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.HTTPRequest;
import com.google.gwt.user.client.ResponseTextHandler;



public class GenericRemoteService implements IRemoteService {

	private ISerializer serializer = new JSONSerializer();
	
	/**
	 * Should be set for environment.
	 * e.g ".php" for php app -> <base>/controller/action.php?param=xx&...
	 * e.g. rails (no ext) -> <base>/controller/action?param=xx&...
	 */
	private String scriptExtension = null;
	
	private String scriptDir = null;
	
	private String controller = null;
	
	private String hostedModeTargetBaseUrl = null;

	///////////////////////////////////////////////////////////////////////////////////////
	
	public GenericRemoteService() {
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private String buildTargetUri(String action) {
		return buildTargetUri(action, null);
	}
	
	private String buildTargetUri(String action, Attributes parameters) {
		String uri = "";
		if(scriptDir != null) {
			uri = scriptDir + "/";
		}
		uri += controller + "/" + action;
		if(scriptExtension != null) {
			uri += scriptExtension;
		}
		if(parameters != null && parameters.size() > 0) {
			uri += "?";
			boolean firstParam = true;
			for (Iterator iter = parameters.iterator(); iter.hasNext();) {
				Attribute attr =  (Attribute) iter.next();
				if(!firstParam) {
					uri += "&";
				}
				uri += attr.getName() + "=" + encodeUrl(attr.getValue().toString());
				firstParam = false;
			}
		}
		return uri;
	}

	/**
	 * Executes a GET on service.
	 * 
	 * @param action the action to execute
	 * @param parameters the query parameters
	 * @param responseHandler the {@link IResponseHandler} to handle the response.
	 */
	public void execute(String action, Attributes parameters, IResponseHandler responseHandler) {
		doGet(buildTargetUri(action, parameters), responseHandler, false);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Executes a GET on service.
	 * 
	 * @param action the action to execute
	 * @param parameters the query parameters
	 * @param responseHandler the {@link IResponseHandler} to handle the response.
	 * @param raw true if we just want to return raw data as a string.
	 */
	public void execute(String action, Attributes parameters, IResponseHandler responseHandler, boolean raw) {
		doGet(buildTargetUri(action, parameters), responseHandler, raw);
	}
	
	/**
	 * Executes a POST on service.
	 */
	public void execute(String action, RemoteObject dataObject, IResponseHandler responseHandler) {
		String data = serializer.serialize(dataObject.serialize());
		doPost(buildTargetUri(action), data, responseHandler);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private String buildFullUrl(String targetUri) {
		if(targetUri.startsWith("/")) {
			// path is absolute
			return targetUri;
		}
		else {
			// path is relative to module's parent
			return getModuleParentPath() + targetUri;
		}
	}
	
	public void doGet(String targetUri, IResponseHandler responseHandler, boolean raw) {
		String realUrl = null;
		if(!GWT.isScript()) {
			// if running in hosted mode, we need to send request to proxy servlet,
			// encoding the "real" target url in the "targeturl" parameter (which needs to be encoded)
			realUrl = GWT.getModuleBaseURL() + "/proxy?targeturl=" + encodeUrl(hostedModeTargetBaseUrl + "/" + targetUri);
		}
		else {
			realUrl = buildFullUrl(targetUri);
		}
		
//		DesktopManager.getInstance().getLogger().logInfo(realUrl);
		
		HTTPRequest.asyncGet(realUrl, new ServiceResponseHandler(responseHandler, raw));
	}
	
	public void doPost(String targetUri, String data, IResponseHandler responseHandler) {
		String realUrl = null;
		if(!GWT.isScript()) {
			// if running in hosted mode, we need to send request to proxy servlet,
			// encoding the "real" target url in the "targeturl" parameter (which needs to be encoded)
			realUrl = GWT.getModuleBaseURL() + "/proxy?targeturl=" + encodeUrl(hostedModeTargetBaseUrl + "/" + targetUri);
		}
		else {
			realUrl = buildFullUrl(targetUri);
		}
		
		HTTPRequest.asyncPost(realUrl, data, new ServiceResponseHandler(responseHandler));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////

	private class ServiceResponseHandler implements ResponseTextHandler {

		IResponseHandler responseHandler;
		boolean raw;
		
		public ServiceResponseHandler(IResponseHandler responseHandler) {
			this(responseHandler, false);
		}
		public ServiceResponseHandler(IResponseHandler responseHandler, boolean raw) {
			this.responseHandler = responseHandler;
			this.raw = raw;
		}
		public void onCompletion(String responseString) {
			if(raw) {
				responseHandler.onCompletion(responseString);
				return;
			}
			RemoteObject obj = null;
			try {
				Attributes attrs = serializer.deserialize(responseString);
				obj = RemoteObjectFactory.getInstance().createObjectInstance(attrs);
			}
			catch(Throwable e) {
				RemoteStatus re = new RemoteStatus("The returned data is not a valid response");
				re.setAttribute("response", responseString);
				re.setAttribute("exception", e);
				re.setStatus(RemoteStatus.STATUS_ERROR);
				obj = re;
			}
			// check this first because ValidationError extends RemoteStatus...
			if(obj instanceof ValidationError) {
				responseHandler.onValidationError((ValidationError) obj);
			}
			else if(obj instanceof RemoteStatus && ((RemoteStatus)obj).isError()) {
				responseHandler.onError((RemoteStatus) obj);
			}
			else {
				responseHandler.onCompletion(obj);
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Gets the parent dir of the GWT module.
	 * 
	 * @return
	 */
	public static String getModuleParentPath() {
		String gwtBaseUrl = GWT.getModuleBaseURL().replaceAll("/gwt/", "/");
		// get value after server name
		String path = gwtBaseUrl.substring(gwtBaseUrl.indexOf("://")+3);
		path = path.substring(path.indexOf('/'));
		return path.replaceAll(GWT.getModuleName()+"/", "");
	}
	
	/**
	 * Calls native javascript encodeURIComponent to encode a url.
	 * 
	 * @param url
	 * @return
	 */
	private static native String encodeUrl(String url) /*-{
    return encodeURIComponent(url);
  }-*/;

	//////////////////////////////////////////////////////////////////////////////
	
	public ISerializer getSerializer() {
		return serializer;
	}

	public void setSerializer(ISerializer serializer) {
		this.serializer = serializer;
	}

	public String getScriptExtension() {
		return scriptExtension;
	}

	public void setScriptExtension(String scriptExtension) {
		this.scriptExtension = scriptExtension;
	}

	public String getScriptDir() {
		return scriptDir;
	}

	public void setScriptDir(String scriptDir) {
		this.scriptDir = scriptDir;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getHostedModeTargetBaseUrl() {
		return hostedModeTargetBaseUrl;
	}

	public void setHostedModeTargetBaseUrl(String hostedModeTargetBaseUrl) {
		this.hostedModeTargetBaseUrl = hostedModeTargetBaseUrl;
	}
}
