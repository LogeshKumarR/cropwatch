package org.cropwatch.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static org.slf4j.LoggerFactory.getLogger;

public class CropInfoJob  implements Job {

    private static final org.slf4j.Logger logger = getLogger(CropInfoJob.class);

    private String cropName;
    private String cronExpression;

    public CropInfoJob() {
    }

    public CropInfoJob(String cropName, String cronExpression) {
        this.cropName = cropName;
        this.cronExpression = cronExpression;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CropInfoJob cropInfoJob = (CropInfoJob) context.getJobDetail().getJobDataMap().get("jobToRun");
        logger.warn("Executing Crop Job For " + cropInfoJob.cropName);
    }

    public String getCropName() {
        return cropName;
    }

    public String getCronExpression() {
        return cronExpression;
    }
}
