package com.tipico.social.marketing.daemon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by chrism on 28/04/2016.
 */
@Configuration
public class QuartzConfiguration {

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		return scheduler;
	}
}