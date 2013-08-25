package org.cropwatch.mapper;


import org.cropwatch.job.CropInfoJob;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobMapper implements ResultSetMapper<CropInfoJob>{

    public CropInfoJob map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new CropInfoJob(resultSet.getString("crop_name"), resultSet.getString("cron_expression"));
    }
}
