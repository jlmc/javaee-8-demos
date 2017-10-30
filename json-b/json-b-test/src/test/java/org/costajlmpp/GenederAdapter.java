package org.costajlmpp;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * @author costa
 * on 30/10/2017.
 */
public class GenederAdapter implements JsonbAdapter<Developer.Gender, JsonObject>{
    @Override
    public JsonObject adaptToJson(Developer.Gender obj) throws Exception {
        Json.createObjectBuilder().add("x", obj.toString());

        return null;
    }

    @Override
    public Developer.Gender adaptFromJson(JsonObject obj) throws Exception {

        final String x = obj.asJsonObject().getString("x");

        return Developer.Gender.valueOf(x);
    }
}
