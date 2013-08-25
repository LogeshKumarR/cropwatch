package org.cropwatch.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

public interface CropMessageDAO extends Transactional<CropMessageDAO> {
    @SqlQuery("select advise from crop_advise where crop_name = :cropName")
    String getAdvise(@Bind("cropName") String cropName);
}
