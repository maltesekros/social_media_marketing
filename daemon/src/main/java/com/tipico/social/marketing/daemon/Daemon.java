package com.tipico.social.marketing.daemon;

import com.tipico.social.marketing.contract.Campaign;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by chrism on 27/04/2016.
 */
@ComponentScan(basePackages = { "com.tipico.social.marketing.daemon" })
public class Daemon implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Daemon.class);
	// Map for current Campaigns
	private Map<Integer, Campaign> campaignMap = new HashMap<>();
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	Scheduler scheduler;

	public static void main(String args[]) {
		SpringApplication.run(Daemon.class);
	}

	private void scheduleCampaign(Campaign campaign, Scheduler scheduler) throws SchedulerException {
		// TODO - Schedule Job depending on Campaign ....
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("message", Integer.toString(new Random().nextInt()));
		JobDetail job1 = newJob(ScheduledJob.class)
			.withIdentity("job1", "group1")
			.setJobData(jobDataMap)
			.build();

		SimpleTrigger trigger1 = newTrigger()
			.withIdentity("trigger1", "group1")
			.startAt(new Date())
			.withSchedule(simpleSchedule()
				.withIntervalInSeconds(10)
				.withRepeatCount(1))
			.build();
		scheduler.scheduleJob(job1, trigger1);
	}

	@Override
	public void run(String... args) throws Exception {
		// Init Scheduler ...
		scheduler = schedulerFactoryBean.getScheduler();
		scheduler.start();
		scheduleCampaign(null, scheduler);
		// Daemon will never die ...
		while (true) {
			Thread.sleep(5000l);

			// TODO - Get Campaigns ...
			List<Campaign> campaigns = new ArrayList<>();
			if (!org.springframework.util.CollectionUtils.isEmpty(campaigns)) {
				for (Campaign campaign : campaigns) {
					// TODO - If campaign should be active start new scheduled job
					if (!campaignMap.containsKey(campaign.getId())) {
						log.info("Scheduling ...");
					}
					// TODO - If campaign should be stopped, do so
				}
			}
		}
	}
}