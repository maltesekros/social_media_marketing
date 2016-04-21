package com.tipico.social.marketing.gateway;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chrism on 21/04/2016.
 */
public class SimpleConnectionSignUp implements ConnectionSignUp {

	private final AtomicLong userIdSequence = new AtomicLong();

	public String execute(Connection<?> connection) {
		return Long.toString(userIdSequence.incrementAndGet());
	}
}
