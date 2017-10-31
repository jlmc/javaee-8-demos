package org.costajlmpp;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;

@Default
@ApplicationScoped
public class TestIdentityStore implements IdentityStore {

    private static final String CALLER_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "secret1";

    private static final String CALLER_EMPLOYER = "employer";
    private static final String PASSWORD_EMPLOYER = "secret1";

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential != null && UsernamePasswordCredential.class.isAssignableFrom(credential.getClass())) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;

            return this.validate(usernamePasswordCredential);

        }

        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }

    private  CredentialValidationResult validate(UsernamePasswordCredential credential) {
        if (credential.compareTo(CALLER_ADMIN, PASSWORD_ADMIN)) {
            return new CredentialValidationResult(CALLER_ADMIN, new HashSet<>(Arrays.asList("ADMIN", "READER")));
        }

        if (credential.compareTo(CALLER_EMPLOYER, PASSWORD_EMPLOYER)) {
            return new CredentialValidationResult(CALLER_EMPLOYER, new HashSet<>(Arrays.asList("EMPLOYER", "READER")));
        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}