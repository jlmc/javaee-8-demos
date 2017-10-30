package org.costajlmpp;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonMergePatch;

public class JsonPatchMergeTest {

    /**
     * Json Merge Patch (IETF RFC 7386 - https://tools.ietf.org/html/rfc7386)
     *
     * Basicamente define as regras sobre o qual o path será aplicado no Json documento, dependendo de como estão relacionados será produzido um output
     *
     * */

    @Test
    public void createMerge() {



        final JsonMergePatch patch = Json.createMergePatch(Json.createObjectBuilder().add("year", 2017).build());

        final JsonArray developers = Developers.developers();

        System.out.println(patch.toJsonValue());
        System.out.println(patch.apply(developers));
    }


}
