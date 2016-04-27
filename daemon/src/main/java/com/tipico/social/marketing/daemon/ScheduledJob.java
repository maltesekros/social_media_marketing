package com.tipico.social.marketing.daemon;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chrism on 27/04/2016.
 */
public class ScheduledJob extends QuartzJobBean {

	private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);
	public static final String SEND_MESSAGE = "http://localhost:8080/social/twitter/send/";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHss");

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext)
		throws JobExecutionException {
		RestTemplate restTemplate = new RestTemplate();
		String message = (String)jobExecutionContext.getMergedJobDataMap().get("message");
		message = message + " " + simpleDateFormat.format(new Date())
;		restTemplate.getForObject(SEND_MESSAGE + message, Object.class);
		log.info("Called Send Method [OK]");
	}
}