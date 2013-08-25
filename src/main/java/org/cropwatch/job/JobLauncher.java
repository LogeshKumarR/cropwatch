package org.cropwatch.job;

import org.cropwatch.dao.CropJobDAO;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.skife.jdbi.v2.DBI;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.slf4j.LoggerFactory.getLogger;

public class JobLauncher {
    private static final org.slf4j.Logger logger = getLogger(JobLauncher.class);
    DBI dbi;

    public JobLauncher(DBI dbi) {
        this.dbi = dbi;
    }

    public void launchAllJobs() throws SchedulerException {

        CropJobDAO cropJobDAO = dbi.onDemand(CropJobDAO.class);
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();

        for (CropInfoJob infoJob : cropJobDAO.getAll()) {
            infoJob.setDbi(dbi);
            logger.warn("Scheduling job - " + infoJob.getCropName());
            JobDetail jobDetail = newJob(CropInfoJob.class)
                    .withIdentity(infoJob.getCropName())
                    .build();
            jobDetail.getJobDataMap().put("jobToRun", infoJob);

            Trigger trigger = newTrigger()
                    .withIdentity(infoJob.getCropName())
                    .startNow()
                    .withSchedule(cronSchedule(infoJob.getCronExpression()))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        }
        scheduler.start();
    }

}
