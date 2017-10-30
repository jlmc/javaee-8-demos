package org.costajlmpp;

import javax.json.Json;
import javax.json.JsonString;
import javax.json.bind.adapter.JsonbAdapter;

public class FirstNameAdapter implements JsonbAdapter<String, JsonString> {

    @Override
    public JsonString adaptToJson(String name) throws Exception {
        return Json.createValue(name.subSequence(0,1).toString());
    }

    @Override
    public String adaptFromJson(JsonString json) throws Exception {
        return json.getString();
    }
}
