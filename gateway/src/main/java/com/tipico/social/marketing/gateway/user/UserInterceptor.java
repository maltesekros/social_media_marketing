package com.tipico.social.marketing.gateway.user;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chrism on 21/04/2016.
 */
public final class UserInterceptor
	extends HandlerInterceptorAdapter {

	private final UsersConnectionRepository connectionRepository;

	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

	public UserInterceptor(UsersConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler)
		throws Exception {
		rememberUser(request, response);
		handleSignOut(request, response);
		return SecurityContext.userSignedIn() || requestForSignIn(request) ||
			requireSignIn(request, response);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
		Object handler,
		Exception ex)
		throws Exception {
		SecurityContext.remove();
	}

	private void handleSignOut(HttpServletRequest request, HttpServletResponse response) {
		if (SecurityContext.userSignedIn() &&
			request.getServletPath().startsWith("/signout")) {
			connectionRepository
				.createConnectionRepository(SecurityContext.getCurrentUser().getId())
				.removeConnections(
					"twitter");
			userCookieGenerator.removeCookie(response);
			SecurityContext.remove();
		}
	}

	private void rememberUser(HttpServletRequest request, HttpServletResponse response) {
		String userId = userCookieGenerator.readCookieValue(request);
		if (userId == null) {
			return;
		}
		if (!userNotFound(userId)) {
			userCookieGenerator.removeCookie(response);
			return;
		}
		SecurityContext.setCurrentUser(new User(userId));
	}

	private boolean requestForSignIn(HttpServletRequest request) {
		return request.getServletPath().startsWith("/signin");
	}

	private boolean requireSignIn(HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		new RedirectView("/signin", true).render(null, request, response);
		return false;
	}

	private boolean userNotFound(String userId) {
		// doesn't bother checking a local user database: simply checks if the userId is connected to Facebook
		return connectionRepository.createConnectionRepository(userId)
			.findPrimaryConnection(Twitter.class) != null;
	}
}
