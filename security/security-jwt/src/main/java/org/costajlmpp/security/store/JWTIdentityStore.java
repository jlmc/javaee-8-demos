package org.costajlmpp.security.store;

import org.costajlmpp.security.entity.JWTCredential;

import javax.annotation.PostConstruct;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Is a component that can access application-specific security data such as users, groups, roles, and permissions.
 * It can be thought of as a security-specific DAO (Data Access Object). Synonyms: security provider, repository, store, login module (JAAS),
 * identity manager, service provider, relying party, authenticator, user service. Identity Stores usually have a 1-to-1 correlation with a data source such as a relational database,
 * LDAP directory, file system, or other similar resource.
 *
 * As such, implementations of the IdentityStore interface use data source-specific APIs to discover authorization data (roles, permissions, etc),
 * such as JDBC, File IO, Hibernate or JPA, or any other Data Access API.
 *
 */
public class JWTIdentityStore implements IdentityStore {

    private Map<String, String> users;

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential == null || !JWTCredential.class.isAssignableFrom(credential.getClass()) ) {

            /*
             * NOT_VALIDATED: Validation was not attempted, because the IdentityStore does not handle the supplied Credential type.
             */
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
            //return CredentialValidationResult.INVALID_RESULT;
        }

        JWTCredential jwtCredential = (JWTCredential) credential;


        // This means we had a valid JWT/JWE.
        String caller = jwtCredential.getCaller();

        Serializable xApiKey = jwtCredential.getInfo(JWTCredential.API_KEY);

        if (xApiKey == null || !xApiKey.equals(this.users.get(caller))) {
            return CredentialValidationResult.INVALID_RESULT;
        }

        // NOTE:: we can overrides the JWT roles :)

        final CredentialValidationResult result = new CredentialValidationResult(caller, jwtCredential.getRoles());
        return result;
    }

    @PostConstruct
    void initUserDB() {
        // TODO In production situations this should not be hardcoded of course.
        users = new HashMap<>(0);
        users.put("Rudy", "49c2b80f-12a5-4464-abad-152cc2cacedb");
        users.put("Soteria", "0a1726c7-068a-4de0-ac64-d27a52cbfce2");
    }
}
