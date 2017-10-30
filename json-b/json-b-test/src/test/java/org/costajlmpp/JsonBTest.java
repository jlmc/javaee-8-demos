package org.costajlmpp;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;



public class JsonBTest {
    /**
     * http://json-b.net/users-guide.html
     */

    Jsonb jsonb;

    @Before
    public void configureJsonB() {
        final JsonbConfig config = new JsonbConfig().
                withFormatting(true).
                withNullValues(true).
                withDateFormat("dd-MM-yyyy", Locale.getDefault());//.withAdapters(new MyDapater());
        this.jsonb = JsonbBuilder.create(config);
    }

    @Test
    public void serialization() {

        Developer developer = new Developer("Joao", Developer.Gender.M, Collections.singletonMap("mobile", "123 456 789"));

        developer.setBorn(LocalDate.now());
        /*developer.setName("Joao");
        developer.setGender(Developer.Gender.M);
        developer.setPhones(Collections.singletonMap("mobile", "123 456 789"));*/

        final String developerAsJson = jsonb.toJson(developer);
        System.out.println(developerAsJson);

        assertNotNull(developerAsJson.isEmpty());
        assertFalse(developerAsJson.isEmpty());
    }

    @Test
    public void deserialization() {
        String developerAsJson = "{\"gender\":\"M\",\"first-name\":\"J\",\"phones\":{\"mobile\":\"1\"}}";

        final Developer developer = jsonb.fromJson(developerAsJson, Developer.class);

        assertNotNull(developer);
        System.out.println(developer);
    }

    @Test
    public void serializationToCollection() {
        Developer developer1 = new Developer("Joao", Developer.Gender.M, Collections.singletonMap("mobile", "123 456 789"));
        Developer developer2 = new Developer("Maria", Developer.Gender.F, Collections.singletonMap("mobile", "999 9999 99"));
        List<Developer> developers = Arrays.asList(developer1, developer2);

        final String developersAsJson = jsonb.toJson(developers);
        System.out.println(developersAsJson);

        assertNotNull(developersAsJson.isEmpty());
        assertFalse(developersAsJson.isEmpty());
    }

    @Test
    public void deserializationFromCollection() {
        String developersAsJson = "[{\"gender\":\"M\",\"first-name\":\"Joao\",\"phones\":{\"mobile\":\"123 456 789\"}}," +
                "{\"gender\":\"F\",\"first-name\":\"Maria\",\"phones\":{\"mobile\":\"999 9999 99\"}}]";

        final List<Developer> developers = jsonb.fromJson(developersAsJson, ArrayList.class);

        assertNotNull(developers);
        assertThat(developers, hasSize(2));
        System.out.println(developers);
    }


    @Test
    @Ignore
    public void lock() {
        long nanos = TimeUnit.NANOSECONDS.convert(5, TimeUnit.SECONDS);

        System.out.println("---" + System.nanoTime());
        LockSupport.parkNanos(nanos);
        System.out.println("---" + System.nanoTime());
        System.out.println("Out");

    }

}
