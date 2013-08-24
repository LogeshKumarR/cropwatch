package org.cropwatch;

import org.cropwatch.service.RegisterationService;
import org.cropwatch.view.RegisterResponseView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cropwatch")
@Produces(MediaType.TEXT_HTML)
public class CropWatchResource {

    private RegisterationService registerationService;

    public CropWatchResource(RegisterationService registerationService) {
        this.registerationService = registerationService;
    }

    @GET
    @Path("/register.xml")
    public RegisterResponseView hello(@QueryParam("txtweb-mobile") String phoneHash,
                                      @QueryParam("txtweb-message") String message) {
        registerationService.register(phoneHash,message);

        return new RegisterResponseView();
    }

}
