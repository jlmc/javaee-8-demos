package org.jcosta.security.handler;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.jcosta.security.JWTTokenHandler;
import org.jcosta.security.entity.JWTCredential;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.text.ParseException;
import java.util.*;

/**
 * This is my custom JWT Validation Handler implementation.
 * @author costa
 * on 03/10/2017.
 */
public class BasicJWTHandler implements JWTTokenHandler {

    private JWKSet jwkSet;

    @Override
    public JWTCredential retrieveCredential(String token) {
        try {
            final JWEObject jweObject = JWEObject.parse(token);
            final String apikey = jweObject.getHeader().getKeyID();

            // use this apiKey to select the correct private Key

            final com.nimbusds.jose.jwk.RSAKey privateKey = getPrivateKey(apikey);

            // Decrypt with shared key
            jweObject.decrypt(new RSADecrypter(privateKey));

            // Extract payload
            SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();

            // Check the HMAC, Optional
            signedJWT.verify(new MACVerifier(apikey));

            // Retrieve the JWT claims...
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            // Verify time validity of token.
            Date creationTime = claimsSet.getIssueTime();
            Date expirationTime = claimsSet.getExpirationTime();
            Date now = new Date();

            long validityPeriod = expirationTime.getTime() - creationTime.getTime();
            if (creationTime.before(now) && now.before(expirationTime) && validityPeriod < 120000 /*2 minutes*/) {

                JSONObject realmAccess = (JSONObject) claimsSet.getClaim("realm_access");

                JSONArray rolesArray = (JSONArray) realmAccess.get("roles");

                Set<String> roles = new HashSet<>();
                rolesArray.forEach(r -> roles.add(r.toString()));

                JWTCredential result = new JWTCredential(claimsSet.getSubject(), roles);
                result.addInfo(API_KEY, apikey);
                result.addInfo(API_KEY, apikey);
                return result;

            }

            return null;

        } catch (ParseException | JOSEException e) {
            // The Token is not valid
            e.printStackTrace();
            return null;
        }

        // TODO:: missing JWT processing Handle

    }

    private com.nimbusds.jose.jwk.RSAKey getPrivateKey(String apikey) {
        final JWK keyByKeyId = jwkSet.getKeyByKeyId(apikey);
        return (com.nimbusds.jose.jwk.RSAKey) keyByKeyId;
    }

    @PostConstruct
    public void initPrivateKeys() {
        String privateContent = readFile("private.jwkset");
        try {
            jwkSet = JWKSet.parse(privateContent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String fileName) {
        InputStream keys = this.getClass().getClassLoader().getResourceAsStream(fileName);
        return new Scanner(keys).useDelimiter("\\Z").next();
    }
}
