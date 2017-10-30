package org.jcosta.hello.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/hello")
public class HelloResource {

    @Inject
    Principal principal;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(@Context SecurityContext context) {
        return "hello " + context.getUserPrincipal().getName() +
                " - " + checkRole(context, "user") +
                " - " + checkRole(context, "manager") +
                " - " + checkRole(context, "nonexisting") +
                " - XXX:::" + principal.getName();
    }

    private String checkRole(SecurityContext context, String roleName) {
        return String.format("Caller has role '%s' ?= %s", roleName, context.isUserInRole(roleName));
    }
}
