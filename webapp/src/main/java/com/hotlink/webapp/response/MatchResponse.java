package com.hotlink.webapp.response;

import org.wechatvr.framework.common.service.rs.api.ServiceResponse;

public class MatchResponse extends ServiceResponse {
	
	private static final long serialVersionUID = 8420525029272021872L;
	
	private boolean matched;

	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}
}
