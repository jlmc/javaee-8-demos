package org.costajlmpp;

import org.junit.Test;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import static org.costajlmpp.Developers.developers;

public class JsonPointerTest {

    @Test
    public void writeToFile() throws FileNotFoundException {
        //final boolean exists = Files.exists("developers0.json");
        // If the first character of your path string is not /, it will be relative to your "current" directory
        //FileOutputStream out = new FileOutputStream("src/test/resources/developers0.json");
        try (final JsonWriter writer = Json.createWriter(new FileOutputStream("src/test/resources/developers0.json"))) {
            writer.writeArray(developers());
        }
    }

    @Test
    public void loadFromFile() {
        JsonArray values = null;
        try (final JsonReader reader = Json.createReader(ClassLoader.class.getResourceAsStream("/developers.json"))) {
            values = reader.readArray();

        }

        assertNotNull(values);
    }

    @Test
    public void getValue() {
        // this is just a pointer, don't have any referency to the document
        JsonPointer pointer = Json.createPointer("/0/phones/mobile");

        JsonString value = (JsonString) pointer.getValue(developers());

        assertThat(value.getString(), is("968989689"));
    }

    @Test
    public void replace() {
        final JsonArray developers = developers();

        JsonPointer pointer = Json.createPointer("/0/phones/mobile");
        JsonArray result = pointer.replace(developers, Json.createValue("123455"));

        assertFalse(developers == result);

        JsonString value = (JsonString) pointer.getValue(result);

        assertThat(value.getString(), is("123455"));


        System.out.println("Original: \n" + developers);
        System.out.println("Original: \n" + result);
    }


    @Test
    public void remove() {
        final JsonArray developers = developers();

        JsonPointer pointer = Json.createPointer("/0/phones/mobile");

        final JsonArray result = pointer.remove(developers);

        assertFalse(developers == result);
        final boolean exists = pointer.containsValue(result);
        assertFalse(exists);


        System.out.println(result);

    }



    @Test
    public void test() {

        JsonArray contacts = developers();

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
