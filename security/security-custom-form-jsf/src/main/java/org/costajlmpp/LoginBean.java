package org.costajlmpp;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml"
                , errorPage = ""
        )
)
@Model
public class LoginBean {

    @Inject
    private javax.security.enterprise.SecurityContext securityContext;

    @NotNull
    @Size(min = 3, max = 15, message="Username must be between 3 and 15 characters")
    private String username;

    @NotNull
    @Size(min = 5, max = 50, message="Password must be between 5 and 50 characters")
    private String password;

    //@Inject private FacesContext facesContext;

    /**
     * We need to validate the credentials using securityContext#authenticate
     */
    public void login () {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        Credential credential =
                new UsernamePasswordCredential(username, new Password(password));

        final AuthenticationStatus status = this.securityContext.authenticate(
                this.getRequest(facesContext),
                this.getResponse(facesContext),
                AuthenticationParameters.withParams().credential(credential)
        );

        if (status.equals(AuthenticationStatus.SEND_CONTINUE)) {
            facesContext.responseComplete();
        } else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
            addError(facesContext, "Authentication failed");
        }

    }

    public void logout() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final HttpServletRequest request = this.getRequest(facesContext);

        try {
            // logout current request
            request.logout();

            // Invalidate session
            request.getSession().invalidate();


        } catch (ServletException e) {
            this.addError(facesContext, "Error Logout - " + e.getMessage());
        }

    }


    private void addError(FacesContext facesContext, String message) {
        facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message,null));
    }

    private HttpServletResponse getResponse(FacesContext facesContext) {
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }

    private HttpServletRequest getRequest(FacesContext facesContext) {
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }


    /**
     * JSF Requires that the beans have Getters and Setters for the fields that are used in the views
     */

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
