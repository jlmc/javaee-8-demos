package org.jcosta.security;

import org.jcosta.security.entity.JWTCredential;

/**
 * This is my custom JWT Validation Handler
 * @author costa
 * on 03/10/2017.
 */
public interface JWTTokenHandler {

    public static final String API_KEY = "xApiKey";

    JWTCredential retrieveCredential(String token);
}
