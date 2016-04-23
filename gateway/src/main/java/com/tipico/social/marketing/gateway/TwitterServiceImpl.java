package com.tipico.social.marketing.gateway;

import org.springframework.stereotype.Component;

/**
 * Created by cmicallef on 19/04/2016.
 */
@Component
public class TwitterServiceImpl implements TwitterService {

	public void tweetMessage(String message) {
		System.out.printf("Tweeting message [text: %s]%n", message);
	}
}
