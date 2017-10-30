package org.costajlmpp;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        JsonObject build = Json.createObjectBuilder().add("name", "Duke").build();

        System.out.println(build );
    }
}
