package org.jcosta;

import javax.persistence.NoResultException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException> {

    @Context
    private HttpHeaders headers;

    @Context
    UriInfo uriDetails;

    @Context
    SecurityContext secContext;

    @Override
    public Response toResponse(NoResultException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new Error("The Resource could not be found."))
                .build();
    }
}
