package org.jcosta.security.mechanism;

import org.jcosta.security.JWTTokenHandler;
import org.jcosta.security.entity.JWTCredential;

import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpAuthenticationMechanism is used to authenticate callers of web applications, and is specified only for use in the servlet container.
 * It is explicitly not defined for use with other containers (EJB, JMS, JCA, etc.).
 * on 03/10/2017.
 */
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {
    private static final String BEARER = "Bearer ";

    @Inject
    private IdentityStore identityStore;

    @Inject
    private JWTTokenHandler tokenHandler;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length());

            JWTCredential credential = tokenHandler.retrieveCredential(token);

            CredentialValidationResult result = identityStore.validate(credential);

            if (CredentialValidationResult.Status.VALID.equals(result.getStatus())) {
                // Communicate the details of the authenticated user to the
                // container. In many cases the underlying handler will just store the details
                // and the container will actually handle the login after we return from
                // this method.
                return httpMessageContext.notifyContainerAboutLogin(
                        result.getCallerPrincipal(), result.getCallerGroups());
            } else {
                throw new AuthenticationException("Login failed");
            }
        }

        return httpMessageContext.doNothing();
    }
}
