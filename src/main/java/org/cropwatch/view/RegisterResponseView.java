package org.cropwatch.view;

import com.yammer.dropwizard.views.View;

public class RegisterResponseView extends View {
    private String status;
    public RegisterResponseView(String status) {
        super("register_response.ftl");
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }
}
