package org.cropwatch.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CropWatchConfiguration extends Configuration {

    @NotNull
    @Valid
    @JsonProperty
    private AppConfiguration appConfig = new AppConfiguration();

    public AppConfiguration getAppConfig() {
        return appConfig;
    }

}
