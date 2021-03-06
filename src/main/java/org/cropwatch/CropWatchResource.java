package org.cropwatch;

import org.cropwatch.service.RegistrationService;
import org.cropwatch.view.RegisterResponseView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cropwatch")
@Produces(MediaType.TEXT_HTML)
public class CropWatchResource {

    private RegistrationService registrationService;

    public CropWatchResource(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GET
    @Path("/register.xml")
    public RegisterResponseView hello(@QueryParam("txtweb-mobile") String phoneHash,
                                      @QueryParam("txtweb-message") String message) {
        String status = "Registration Successful";
        try {
            registrationService.register(phoneHash,message);
        } catch(Exception e) {
            status = "Think that , you have already registerd";
            e.printStackTrace();
        }

        return new RegisterResponseView(status);
    }

}
