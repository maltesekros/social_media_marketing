package com.tipico.social.marketing.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@PostConstruct
	private void init() {
		System.out.println("Init");
	}

	@RequestMapping("/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Callback [oauthToken: " + request.getParameter("oauth_token") + "] [oauthVerifier: " + request.getParameter("oauth_verifier") + "]");
		String requestToken = request.getParameter("oauth_token");
		String oauthVerifier = request.getParameter("oauth_verifier");
		TwitterConnectionFactory connectionFactory =
			new TwitterConnectionFactory(consumerKey, consumerSecret);
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken accessToken = oauthOperations.exchangeForAccessToken(new AuthorizedRequestToken(new OAuthToken(requestToken, consumerSecret), oauthVerifier), null);
		twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken.getValue(), accessToken.getSecret());
		List<Long> friendIds = twitter.friendOperations().getFollowerIds();
		System.out.println("You have " + friendIds.size() + " followers. ");
	}

	@RequestMapping(value = "/send/{message}", method = RequestMethod.GET)
	public
	@ResponseBody
	void sendTweetMessage(@PathVariable String message, HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		TwitterConnectionFactory connectionFactory =
			new TwitterConnectionFactory(consumerKey, consumerSecret);
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken = oauthOperations.fetchRequestToken( "http://127.0.0.1:8080/social/twitter/callback", null );
		String authorizeUrl = oauthOperations.buildAuthorizeUrl( requestToken.getValue(), OAuth1Parameters.NONE );
		response.sendRedirect( authorizeUrl );
	}
}
