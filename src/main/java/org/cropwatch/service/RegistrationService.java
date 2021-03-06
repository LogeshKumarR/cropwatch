package org.cropwatch.service;

import org.cropwatch.dao.FarmerCropDAO;
import org.skife.jdbi.v2.DBI;

public class RegistrationService {

    DBI dbi;

    public RegistrationService(DBI dbi) {
        this.dbi = dbi;
    }

    public void register(String phoneHash,String cropName) {
        FarmerCropDAO farmerCropDAO = dbi.onDemand(FarmerCropDAO.class);
        farmerCropDAO.register(phoneHash, cropName);
    }
}
