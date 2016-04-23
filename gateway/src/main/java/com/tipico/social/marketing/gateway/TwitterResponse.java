package com.tipico.social.marketing.gateway;

/**
 * Created by cmicallef on 20/04/2016.
 */
public class TwitterResponse {

	private final int code;
	private final String message;

	public TwitterResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
