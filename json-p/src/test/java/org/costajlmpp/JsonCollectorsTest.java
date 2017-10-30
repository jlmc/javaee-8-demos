package org.costajlmpp;

import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import java.util.List;
import java.util.stream.Collectors;

public class JsonCollectorsTest {

    @Test
    public void collectors() {

        // exract the names
        final List<String> collect = Developers.developers().
                getValuesAs(JsonObject.class).
                stream().
                filter(x -> "M".equals(x.getString("gender"))).
                map(o -> o.getString("name")).
                collect(Collectors.toList());


        System.out.println(collect);

        // In the JSOn World exract the names
        JsonArray a = Developers.developers().
                getValuesAs(JsonObject.class).stream().
                filter(x -> "M".equals(x.getString("gender"))).
                map(x -> x.get("name")).
                collect(JsonCollectors.toJsonArray());

        System.out.println(a);
    }
}
