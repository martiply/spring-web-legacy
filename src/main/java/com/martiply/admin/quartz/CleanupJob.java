package com.martiply.admin.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CleanupJob extends QuartzJobBean{
	
	CleanupTask cleanupTask;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		cleanupTask.process();
	}

	
	public void setCleanupTask(CleanupTask cleanupTask) {
		this.cleanupTask = cleanupTask;
	}
	
	
}
