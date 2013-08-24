package org.cropwatch.controller;

import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Controller
@Path("/hello")
public class HelloWorldController {

    @GET
    @Path("/{name}.json")
    public String hello(@PathParam("name") String name) {
        return "Hello " + name;
    }
}
