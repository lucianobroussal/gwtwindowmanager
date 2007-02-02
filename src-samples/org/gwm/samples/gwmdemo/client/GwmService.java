package org.gwm.samples.gwmdemo.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface GwmService extends RemoteService{

	public String echo();
	
	public void registerVisit(boolean newVisitor);
	
	public Stats getStats();
	
	public String registerMailingList(String nickname, String email);

}
