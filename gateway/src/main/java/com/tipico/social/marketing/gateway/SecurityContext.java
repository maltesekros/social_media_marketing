package com.tipico.social.marketing.gateway;

/**
 * Created by chrism on 21/04/2016.
 */
public final class SecurityContext {

	private static final ThreadLocal<User> currentUser = new ThreadLocal<User>();

	public static User getCurrentUser() {
		User user = currentUser.get();
		if (user == null) {
			throw new IllegalStateException("No user is currently signed in");
		}
		return user;
	}

	public static void setCurrentUser(User user) {
		currentUser.set(user);
	}

	public static void remove() {
		currentUser.remove();
	}

	public static boolean userSignedIn() {
		return currentUser.get() != null;
	}
}