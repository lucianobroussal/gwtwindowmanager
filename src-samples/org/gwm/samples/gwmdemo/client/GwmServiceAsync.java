package org.gwm.samples.gwmdemo.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwmServiceAsync {

	public void echo(AsyncCallback callback);

	public void registerVisit(boolean newVisitor, AsyncCallback callback);

	public void getStats(AsyncCallback callback);

	public void registerMailingList(String nickname, String email , AsyncCallback callback);
}
