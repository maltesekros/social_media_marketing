package com.tipico.social.marketing.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Campaign {

	@Id
	private String id;

	private String eventName;
	private Date created;
	private Date lastUpdated;
	private Date startDate;
	private Date endDate;
	private int delayBetweenPosts;
	private String message;

	public Campaign() {}

	public Campaign(String eventName, Date created, Date lastUpdated, Date startDate, Date endDate, int delayBetweenPosts, String message) {
		this.eventName = eventName;
		this.created = created;
		this.lastUpdated = lastUpdated;
		this.startDate = startDate;
		this.endDate = endDate;
		this.delayBetweenPosts = delayBetweenPosts;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDelayBetweenPosts() {
		return delayBetweenPosts;
	}

	public void setDelayBetweenPosts(int delayBetweenPosts) {
		this.delayBetweenPosts = delayBetweenPosts;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
