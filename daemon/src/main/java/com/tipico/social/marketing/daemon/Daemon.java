package com.tipico.social.marketing.daemon;

import com.tipico.social.marketing.contract.Campaign;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by chrism on 27/04/2016.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.tipico.social.marketing.daemon" })
public class Daemon implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Daemon.class);
	// Map for current Campaigns
	private Map<String, Campaign> campaignMap = new HashMap<>();
	@Autowired
	private CampaignService campaignService;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	private Scheduler scheduler;
	@Value("${daemon.thread.sleepMs}")
	private String daemonMainThreadSleepTime;

	public static void main(String args[]) {
		SpringApplication.run(Daemon.class);
	}

	private void scheduleCampaign(Campaign campaign) throws SchedulerException {
		log.info("-----------------");
		log.info("Scheduling campaign: " + campaign);
		log.info("-----------------");
		//Create Job
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("message", campaign.getMessage());
		JobDetail job1 = newJob(ScheduledJob.class)
			.withIdentity("camp" + campaign.getId(), "group1")
			.setJobData(jobDataMap)
			.build();
		// Create Trigger
		SimpleTrigger trigger1 = newTrigger()
			.withIdentity("trig" + campaign.getId(), "group1")
			.startAt(campaign.getStartDate())
			.endAt(campaign.getEndDate())
			.withSchedule(simpleSchedule()
			.repeatForever()
			.withIntervalInMilliseconds(campaign.getDelayBetweenPosts()))
			.build();
		scheduler.scheduleJob(job1, trigger1);
		campaignMap.put(campaign.getId(), campaign);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting Daemon ...");
		// Init Scheduler ...
		scheduler = schedulerFactoryBean.getScheduler();
		scheduler.start();

		// Daemon will never die ...
		while (true) {
			log.info(
				String.format("No. of scheduled Campaigns [no: %d]", campaignMap.size()));
			Thread.sleep(Long.valueOf(daemonMainThreadSleepTime));
			List<Campaign> campaigns = campaignService.getAllCampaigns();
			if (!org.springframework.util.CollectionUtils.isEmpty(campaigns)) {
				for (Campaign campaign : campaigns) {
					Date now = new Date();
					// Campaign is active
					if (campaign.getEndDate().after(now)) {
						// Check if it has been already scheduled ...
						if (!campaignMap.containsKey(campaign.getId())) {
							log.info(String
								.format("Scheduling campaign [id: %s]", campaign.getId()));
							scheduleCampaign(campaign);
						}
						else {
							log.info(String
								.format("Campaign [id: %s] is already scheduled ...",
									campaign.getId()));
						}
					}
					else {
						if (campaignMap.containsKey(campaign.getId())) {
							campaignMap.remove(campaign.getId());
							log.info(String.format("Campaign [id: %s] removed from map. ",
								campaign.getId()));
						}
					}
				}
			}
		}
	}
}