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
package org.gwm.splice.client.util.js;


/**
 * Contains some general Javascsript helpers.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public class JSUtils {

	
	/**
	 * Dynamically load a remote javascript file.
	 * 
	 * @param source the 'src' attribute.
	 */
	public static native void loadJavascript(String source) /*-{
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = source;
		document.getElementsByTagName('head')[0].appendChild(script);  
}-*/; 

	/**
	 * Dynamically load a remote CSS file.
	 * 
	 * @param source the 'href' attribute.
	 */
	public static native void loadCSS(String source) /*-{
		var link = document.createElement('link');
		link.type = 'text/css';
		link.href = source;
		link.rel = 'stylesheet';
		document.getElementsByTagName('head')[0].appendChild(link);  
}-*/; 

	/**
	 * Read a JS attribute string value.
	 * 
	 * @param name the name of the JS variable.
	 * @return a String value or null if not found.
	 */
	public static native String getJSVar(String name) /*-{
		if($wnd[name])
			return $wnd[name];
		
		return null;
}-*/; 


}
