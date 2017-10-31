package org.costajlmpp.present.boundary;

import org.costajlmpp.present.entity.Developer;

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
