package com.hotlink.webapp.request;

import java.io.Serializable;

public class MatchRequest implements Serializable {

	private String pic;
	private int activityId;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
