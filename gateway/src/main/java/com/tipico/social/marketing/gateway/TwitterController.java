package com.tipico.social.marketing.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

/**
 * Created by cmicallef on 19/04/2016.
 */
@Controller
@RequestMapping("/social/twitter")
public class TwitterController {

	private Twitter twitter;
	@Value("${twitter.consumerKey}")
	private String consumerKey;
	@Value("${twitter.consumerSecret}")
	private String consumerSecret;

	@PostConstruct
	private void init() {
		twitter = new TwitterTemplate(consumerKey, consumerSecret);
	}

	@RequestMapping(value = "{message}", method = RequestMethod.GET, headers = {
		"Content-type=application/json" })
	public
	@ResponseBody
	TwitterResponse sendTweetMessage(@PathVariable String message) {
		System.out.println(twitter.friendOperations().getFollowerIds());
		return new TwitterResponse(0, "OK");
	}
}
