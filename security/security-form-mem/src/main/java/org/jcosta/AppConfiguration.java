package org.jcosta;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage="/login.html",
                errorPage="/login-error.html"
        )
)
@ApplicationScoped
public class AppConfiguration {}
