package org.jcosta.present.boundary;

import org.jcosta.present.entity.Developer;

import javax.resource.spi.ConfigProperty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("developers")
public class DeveloperResource {

    @GET
    public String getDeveloper() {
        Developer developer = new Developer("Joao Costa", 23);
        return developer.toJson();
    }

}
