package com.tipico.social.marketing.daemon;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chrism on 27/04/2016.
 */
public class ScheduledJob extends QuartzJobBean {

	private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);
	// TODO - Add to properties
	public static final String SEND_MESSAGE = "http://127.0.0.1:8081/social/twitter/send";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHmmss");

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext)
		throws JobExecutionException {
		RestTemplate restTemplate = new RestTemplate();
		String message = (String)jobExecutionContext.getMergedJobDataMap().get("message");
		message = message + " " + simpleDateFormat.format(new Date());
		try {
			log.info(String.format("Calling [url: %s]", SEND_MESSAGE));
			restTemplate.postForEntity(SEND_MESSAGE, message, String.class);
			log.info("Called Send Method [OK]");
		}
		catch (RestClientException re) {
			log.error("Error posting to Social Gateway. ", re);
		}
	}
}