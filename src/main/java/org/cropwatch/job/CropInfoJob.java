package org.cropwatch.job;

import org.cropwatch.dao.CropMessageDAO;
import org.cropwatch.dao.FarmerCropDAO;
import org.cropwatch.service.SmsService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.skife.jdbi.v2.DBI;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class CropInfoJob  implements Job {

    private static final org.slf4j.Logger logger = getLogger(CropInfoJob.class);

    private String cropName;
    private String cronExpression;
    private DBI dbi;

    public CropInfoJob() {
    }

    public CropInfoJob(String cropName, String cronExpression) {
        this.cropName = cropName;
        this.cronExpression = cronExpression;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CropInfoJob cropInfoJob = (CropInfoJob) context.getJobDetail().getJobDataMap().get("jobToRun");
        CropMessageDAO cropMessageDao = cropInfoJob.dbi.onDemand(CropMessageDAO.class);
        String adviseMessage = cropMessageDao.getAdvise(cropInfoJob.getCropName());
        FarmerCropDAO farmerCropDAO = cropInfoJob.dbi.onDemand(FarmerCropDAO.class);
        List<String> phoneHashes = farmerCropDAO.getPhoneHash(cropInfoJob.getCropName());
        for (String phoneHash : phoneHashes) {
            new SmsService().sendPushMessage(adviseMessage,phoneHash);
        }
        logger.warn("Executing Crop Job For " + cropInfoJob.cropName);
    }

    public String getCropName() {
        return cropName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setDbi(DBI dbi) {
        this.dbi = dbi;
    }
}
