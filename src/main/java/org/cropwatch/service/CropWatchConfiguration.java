package org.cropwatch.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CropWatchConfiguration extends Configuration {

    @NotNull
    @Valid
    @JsonProperty
    private DatabaseConfiguration cropwatchDBConfig = new DatabaseConfiguration();

    public DatabaseConfiguration getCropwatchDBConfig() {
        return cropwatchDBConfig;
    }
}
