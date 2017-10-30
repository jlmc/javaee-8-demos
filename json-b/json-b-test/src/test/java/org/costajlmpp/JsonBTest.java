package org.costajlmpp;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

import static org.hamcrest.MatcherAssert.assertThat;



public class JsonBTest {
    /**
     * http://json-b.net/users-guide.html
     */

    @Test
    public void serialization() {

        Developer developer = new Developer("Joao", Developer.Gender.M, Collections.singletonMap("mobile", "123 456 789"));
        /*developer.setName("Joao");
        developer.setGender(Developer.Gender.M);
        developer.setPhones(Collections.singletonMap("mobile", "123 456 789"));*/


        final Jsonb jsonb = JsonbBuilder.create();

        final String developerAsJson = jsonb.toJson(developer);
        System.out.println(developerAsJson);

        assertNotNull(developerAsJson.isEmpty());
        assertFalse(developerAsJson.isEmpty());
    }

    @Test
    public void deserialization() {
        String developerAsJson = "{\"gender\":\"M\",\"name\":\"Joao\",\"phones\":{\"mobile\":\"123 456 789\"}}";

        final Jsonb jsonb = JsonbBuilder.create();

        final Developer developer = jsonb.fromJson(developerAsJson, Developer.class);

        assertNotNull(developer);
        System.out.println(developer);

    }


    @Test
    public void serializationToCollection() {
        Developer developer1 = new Developer("Joao", Developer.Gender.M, Collections.singletonMap("mobile", "123 456 789"));
        Developer developer2 = new Developer("Maria", Developer.Gender.F, Collections.singletonMap("mobile", "999 9999 99"));
        List<Developer> developers = Arrays.asList(developer1, developer2);

        final Jsonb jsonb = JsonbBuilder.create();

        final String developersAsJson = jsonb.toJson(developers);
        System.out.println(developersAsJson);

        assertNotNull(developersAsJson.isEmpty());
        assertFalse(developersAsJson.isEmpty());
    }

    @Test
    public void deserializationFromCollection() {
        String developersAsJson = "[{\"gender\":\"M\",\"name\":\"Joao\",\"phones\":{\"mobile\":\"123 456 789\"}},{\"gender\":\"F\",\"name\":\"Maria\",\"phones\":{\"mobile\":\"999 9999 99\"}}]";

        final Jsonb jsonb = JsonbBuilder.create();

        final List<Developer> developers = jsonb.fromJson(developersAsJson, ArrayList.class);

        assertNotNull(developers);
        assertThat(developers, hasSize(2));
        System.out.println(developers);
    }

}
