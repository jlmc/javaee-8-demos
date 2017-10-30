package org.costajlmpp;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonPatch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JsonPatchTest {

    /**
     * https://tools.ietf.org/html/rfc6902
     *
     * 1 - JSON Path é por si só tambem um JSon document
     * 2 - reflete uma sequencia de operações a serem feitas sobre um documento target, a norma IETF RFC 6902 definiu quais são essas operações
     *      - add
     *      - remove
     *      - replace
     *      - move
     *      - copy
     *      - test
     */


    /**
     * JsonPatchBuilder é a classe Builder Obtida atraves do Json esse builder disponibiliza tambem todos os metodos da Class Json Patch.
     */

    /**
     * A Class Patch disponibiliza dois metodos importantes
     *  apply() e toJSonArray()
     */

    @Test
    public void createJsonPatch() {

        final JsonPatch patch = Json.createPatchBuilder().
                add("/0/active", true).
                remove("/0/phones/mobile").
                build();

        assertNotNull(patch);
        System.out.println(patch);
    }

    @Test
    public void apply() {
        final JsonArray developers = Developers.developers();


        final JsonPatch patch = Json.createPatchBuilder().
                add("/0/active", true).
                add("/1/active", true).
                add("/2/active", false).
                remove("/0/phones/mobile").
                replace("/1/phones/home", "99999999").
                build();

        final JsonArray result = patch.apply(developers);

        assertFalse(developers == result);
        System.out.println(result);
    }

    @Test
    public void toArray() {
        final JsonArray developers = Developers.developers();


        final JsonPatch patch = Json.createPatchBuilder().
                add("/0/active", true).
                add("/1/active", true).
                add("/2/active", false).
                remove("/0/phones/mobile").
                replace("/1/phones/home", "99999999").
                build();

        final JsonArray jsonValues = patch.toJsonArray();

        System.out.println(jsonValues);
    }

}
