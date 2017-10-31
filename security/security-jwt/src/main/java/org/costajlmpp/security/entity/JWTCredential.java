package org.costajlmpp.security.entity;

import javax.security.enterprise.credential.Credential;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author costa
 * on 03/10/2017.
 */
public class JWTCredential implements Credential {

    public static final String API_KEY = "xApiKey";

    private String caller;
    private Map<String, Serializable> info;
    private Set<String> roles;

    public JWTCredential(String caller) {
        this(caller, Collections.emptySet());
    }

    public JWTCredential(String caller, Set<String> roles) {
        this.caller = caller;
        this.roles = roles;
        info = new HashMap<>(0);
    }

    public String getCaller() {
        return caller;
    }

    public void addInfo(String key, Serializable value) {
        info.put(key, value);
    }

    public Set<String> getRoles() {
        return roles;
    }

    // TODO Consider Generic Type like
    //public <T> T getInfo(String key)
    public Serializable getInfo(String key) {
        return info.get(key);
    }
}
