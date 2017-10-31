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

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (UsernamePasswordCredential.class.isAssignableFrom(credential.getClass())) {
            return this.validate((UsernamePasswordCredential) credential);
        }

        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }

    private CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        if (usernamePasswordCredential.compareTo("costajlmpp", "secret1")) {
            return new CredentialValidationResult("costajlmpp", new HashSet<>(Arrays.asList("ADMIN", "VIEW")));
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

}
