package com.bhz.lht.mystudy;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

public class QuartzSample {
	
	public static void main(String[] args) throws InterruptedException {

		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// and start it off
			scheduler.start();

			// Type #1: define the job and tie it to our HelloJob class
			JobDetail job1 = newJob(HelloJob.class).withIdentity("job1", "group1").build();

			// Trigger the job to run now, and then repeat every 40 seconds
			Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow()
					.withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job1, trigger);

			// Type #2: define the job and tie it to our HelloJob class
			JobDetail job2 = newJob(HelloJob.class).withIdentity("job2", "group2").build();

			// or cron trigger:
			CronTrigger ctrigger = newTrigger().withIdentity("trigger2", "group2")
					.withSchedule(cronSchedule("*/5 * * * * ?")).build();
			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job2, ctrigger);

			Thread.sleep(Integer.MAX_VALUE);

			scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}
