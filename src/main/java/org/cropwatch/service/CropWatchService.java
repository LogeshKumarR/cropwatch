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
    public void initialize(Bootstrap cropwatchConfiguraitonBootstrap) {
        cropwatchConfiguraitonBootstrap.setName("Pipe Service");
        cropwatchConfiguraitonBootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        cropwatchConfiguraitonBootstrap.addBundle(new ViewBundle());
        cropwatchConfiguraitonBootstrap.addBundle(new MigrationsBundle<CropWatchConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(CropWatchConfiguration configuration) {
                return configuration.getCropwatchDBConfig();
            }
        });

    }

    @Override
    public void run(CropWatchConfiguration configuration, Environment environment) throws Exception {

        DBI dbi = getDBI(configuration.getCropwatchDBConfig(), environment, "Cropwatch DB");
        RegisterationService registerationService = new RegisterationService(dbi);

        CropWatchResource cropResource = new CropWatchResource(registerationService);
        environment.addResource(cropResource);

        logger.warn("Starting Scheduler!!!");
    }

    protected DBI getDBI(DatabaseConfiguration databaseConfiguration, Environment environment, String dbiName) throws ClassNotFoundException {
        return new DBIFactory().build(environment, databaseConfiguration, dbiName);
    }
}
