package com.tipico.social.marketing.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cmicallef on 19/04/2016.
 */
@Controller
@RequestMapping("/social/twitter")
public class TwitterController {

	public static final String CALLBACK_URL = "http://127.0.0.1:8081/social/twitter/callback";
	private Twitter twitter;
	@Value("${twitter.consumerKey}")
	private String consumerKey;
	@Value("${twitter.consumerSecret}")
	private String consumerSecret;
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
	private String accessToken;
	private String accessSecret;

	@RequestMapping("/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) {
		System.out.printf("Callback [oauthToken: %s] [oauthVerifier: %s]%n",
			request.getParameter("oauth_token"), request.getParameter("oauth_verifier"));
		String requestToken = request.getParameter("oauth_token");
		String oauthVerifier = request.getParameter("oauth_verifier");
		TwitterConnectionFactory connectionFactory =
			new TwitterConnectionFactory(this.consumerKey, this.consumerSecret);
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken accessToken = oauthOperations.exchangeForAccessToken(
			new AuthorizedRequestToken(new OAuthToken(requestToken, consumerSecret),
				oauthVerifier), null);
		this.accessToken = accessToken.getValue();
		this.accessSecret = accessToken.getSecret();
		this.twitter = new TwitterTemplate(this.consumerKey, this.consumerSecret,
			this.accessToken, this.accessSecret);
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public
	@ResponseBody String sendTweetMessage(@RequestBody String message, HttpServletRequest request,
		HttpServletResponse response)
		throws IOException {
		if (StringUtils.isEmpty(message)) {
			throw new IOException("Cannot send a null or empty message. ");
		}
		if (twitter == null) {
			System.out.println("Authorisation not done yet ... ");
			authorise(response);
		} else {
			twitter.timelineOperations().updateStatus(message);
			System.out.println("------------------------");
			System.out.println("Message sent [OK]");
			System.out.println("------------------------");

		}
		return "OK";
	}

	private void authorise(HttpServletResponse response) throws IOException {
		TwitterConnectionFactory connectionFactory =
			new TwitterConnectionFactory(this.consumerKey, this.consumerSecret);
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken = oauthOperations
			.fetchRequestToken(CALLBACK_URL, null);
		String authorizeUrl = oauthOperations
			.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);
		response.sendRedirect(authorizeUrl);
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public
	@ResponseBody
	void start(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		if (twitter == null) {
			authorise(response);
		}
	}
}
