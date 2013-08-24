package org.cropwatch.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

public interface FarmerCropDAO extends Transactional<FarmerCropDAO> {
    @SqlUpdate("insert into farmer_crop(phone_hash, crop_name) " +
            "values (:phoneHash, :cropName)")
    void register(@Bind("phoneHash") String phoneHash, @Bind("cropName") String cropName);
}
