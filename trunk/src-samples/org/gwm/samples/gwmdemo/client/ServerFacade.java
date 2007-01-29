package org.gwm.samples.gwmdemo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ServerFacade {

	private String staticResponseURL;

	private GwmServiceAsync remoteService;

	private static ServerFacade instance;

	private ServerFacade() {
		staticResponseURL = GWT.getModuleBaseURL();
		if (staticResponseURL.equals(""))
			staticResponseURL = "/";

	}

	public String getBaseUrl() {
		return staticResponseURL;
	}

	public GwmServiceAsync getRemoteService() {
		if (remoteService == null) {
			remoteService = (GwmServiceAsync) GWT.create(GwmService.class);
			((ServiceDefTarget) remoteService)
					.setServiceEntryPoint(staticResponseURL + "gwmsite");
		}
		return remoteService;
	}

	public static ServerFacade get() {
		if (instance == null) {
			instance = new ServerFacade();
		}
		return instance;
	}

}