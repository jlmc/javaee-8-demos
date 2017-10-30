package org.costajlmpp.security;

import org.costajlmpp.security.entity.JWTCredential;

/**
 * This is my custom JWT Validation Handler
 * @author costa
 * on 03/10/2017.
 */
public interface JWTTokenHandler {

    public static final String API_KEY = "xApiKey";

    JWTCredential retrieveCredential(String token);
}
