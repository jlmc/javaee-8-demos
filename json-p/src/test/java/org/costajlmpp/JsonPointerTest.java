package org.costajlmpp;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonPointer;
import javax.json.JsonValue;

public class JsonPointerTest {

    @Test
    public void test() {

        JsonArray contacts = Json.createArrayBuilder().
                add(Json.createObjectBuilder().
                        add("name", "Duke").
                        add("gender", "M").
                        add("phones", Json.createObjectBuilder().
                                add("home", "239 641 840").
                                add("mobile", "968989689").
                                build()).
                        build()
                ).
                add(Json.createObjectBuilder().
                        add("name", "Joao").
                        add("gender", "M").
                        add("phones", Json.createObjectBuilder().
                                add("home", "239 641 999").
                                add("mobile", "91989898").
                                build()).
                        build()
                ).
                build();
        System.out.println(contacts);

        // this is just a pointer, don't have any referency to the document
        JsonPointer pointer = Json.createPointer("/0/phones/mobile");


        // para manipularmos o documento passamos o documento target como parametro na operação que pretendemos
        JsonValue value = pointer.getValue(contacts);

        System.out.println("--- " + value);


        JsonArray replace = pointer.replace(contacts, Json.createValue("123455"));

        System.out.println(replace);
        System.out.println("---");
        System.out.println(contacts);

    }
}
