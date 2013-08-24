package org.cropwatch.service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.cropwatch.CropWatchResource;
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
    public void initialize(Bootstrap pipeConfiguraitonBootstrap) {
        pipeConfiguraitonBootstrap.setName("Pipe Service");
        pipeConfiguraitonBootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(CropWatchConfiguration configuration, Environment environment) throws Exception {

        CropWatchResource cropResource = new CropWatchResource();
        environment.addResource(cropResource);

        logger.warn("Starting Scheduler!!!");
    }
}
