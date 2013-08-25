package org.cropwatch.service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.migrations.MigrationsBundle;
import com.yammer.dropwizard.views.ViewBundle;
import org.cropwatch.CropWatchResource;
import org.cropwatch.job.JobLauncher;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CropWatchService extends Service<CropWatchConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(CropWatchService.class);

    public CropWatchService() {
    }

    public static void main(String[] args) throws Exception {
        new CropWatchService().run(args);
    }

    @Override
    public void initialize(Bootstrap bootstrap) {
        bootstrap.setName("CropWatch Service");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new MigrationsBundle<CropWatchConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(CropWatchConfiguration configuration) {
                return configuration.getCropwatchDBConfig();
            }
        });

    }

    @Override
    public void run(CropWatchConfiguration configuration, Environment environment) throws Exception {

        DBI dbi = getDBI(configuration.getCropwatchDBConfig(), environment, "Cropwatch DB");
        RegistrationService registrationService = new RegistrationService(dbi);
        new JobLauncher(dbi).launchAllJobs();

        CropWatchResource cropResource = new CropWatchResource(registrationService);
        environment.addResource(cropResource);

        logger.warn("Starting Scheduler!!!");
    }

    protected DBI getDBI(DatabaseConfiguration databaseConfiguration, Environment environment, String dbiName) throws ClassNotFoundException {
        return new DBIFactory().build(environment, databaseConfiguration, dbiName);
    }
}
