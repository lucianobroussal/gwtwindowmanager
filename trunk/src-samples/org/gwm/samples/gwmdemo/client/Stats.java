package org.gwm.samples.gwmdemo.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Stats implements IsSerializable {

	private long visitorCount;

	private long visitCount;

	public long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(long visitCount) {
		this.visitCount = visitCount;
	}

	public long getVisitorCount() {
		return visitorCount;
	}

	public void setVisitorCount(long visitorCount) {
		this.visitorCount = visitorCount;
	}
}
