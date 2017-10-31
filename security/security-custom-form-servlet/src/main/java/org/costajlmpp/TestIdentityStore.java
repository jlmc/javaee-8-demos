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

    private static final String CALLER = "costajlmpp";
    private static final String PASSWORD = "secret1";

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential != null && UsernamePasswordCredential.class.isAssignableFrom(credential.getClass())) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;

            if (((UsernamePasswordCredential) credential).compareTo(CALLER, PASSWORD)) {
                return new CredentialValidationResult(CALLER, new HashSet<>(Arrays.asList("foo", "bar")));
            }

        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}
