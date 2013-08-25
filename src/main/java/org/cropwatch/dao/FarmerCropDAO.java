package org.cropwatch.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

public interface FarmerCropDAO extends Transactional<FarmerCropDAO> {
    @SqlUpdate("insert into farmer_crop(phone_hash, crop_name) " +
            "values (:phoneHash, :cropName)")
    void register(@Bind("phoneHash") String phoneHash, @Bind("cropName") String cropName);

    @SqlQuery("select phone_hash from farmer_crop where crop_name = :cropName")
    List<String> getPhoneHash(@Bind("cropName") String cropName);
}
