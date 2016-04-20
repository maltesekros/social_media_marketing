package com.tipico.social.marketing.contract;

import java.util.Date;
import org.springframework.data.annotation.Id;

public class Campaign {

<<<<<<< HEAD
	@Id
	private String id;
	private String eventName;
	private Date created;
	private Date lastUpdated;
	private Date startDate;
	private Date endDate;
	private int delayBetweenPosts;
	private String message;

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
=======
    private Integer id;
    private String eventName;
    private Date created;
    private Date lastUpdated;
    private Date startDate;
    private Date endDate;
    private int delayBetweenPosts;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
>>>>>>> master
}
