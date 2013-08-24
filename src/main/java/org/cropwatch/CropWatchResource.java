package org.cropwatch;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cropwatch")
@Produces(MediaType.APPLICATION_JSON)
public class CropWatchResource {

    @GET
    @Path("/hello/{name}.json")
    public String hello(@PathParam("name") String name) {
        return "Hello "+name;
    }

}
