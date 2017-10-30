package com.jcosta.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;


// definition can be in the top of any bean
@BasicAuthenticationMechanismDefinition( realmName = "jaxrs")


// the specification says that the beans must have @Default Qualifier and @ApplicationScoped Scope
@Default
@ApplicationScoped
public class BasicIdentityStore implements IdentityStore {

    private static final String CALLER = "costajlmpp";
    private static final String PASSWORD = "secret1";


        @Override
        public CredentialValidationResult validate(Credential credential) {

            if (credential != null && UsernamePasswordCredential.class.isAssignableFrom(credential.getClass())) {
                UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;

               return this.validate(usernamePasswordCredential);

            }

            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }

        private CredentialValidationResult validate(UsernamePasswordCredential credential) {

            if ((credential).compareTo(CALLER, PASSWORD)) {
                return new CredentialValidationResult(CALLER, new HashSet<>(Arrays.asList("READ", "WRITE")));
            }

            return CredentialValidationResult.INVALID_RESULT;
        }
}
