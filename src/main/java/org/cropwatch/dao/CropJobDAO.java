package org.cropwatch.dao;

import org.cropwatch.job.CropInfoJob;
import org.cropwatch.mapper.JobMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(JobMapper.class)
public interface CropJobDAO extends Transactional<CropJobDAO> {

    @SqlQuery("select * from crop_schedules")
    List<CropInfoJob> getAll();
}
