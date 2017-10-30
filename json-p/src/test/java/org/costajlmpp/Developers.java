package org.costajlmpp;

import javax.json.Json;
import javax.json.JsonArray;

public class Developers {

    static JsonArray developers () {
        JsonArray developers = Json.createArrayBuilder().
                add(Json.createObjectBuilder().
                        add("name", "Nuno").
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
                add(Json.createObjectBuilder().
                        add("name", "Maria").
                        add("gender", "M").
                        add("phones", Json.createObjectBuilder().
                                add("home", "123 456 789").
                                add("mobile", "91989898").
                                build()).
                        build()
                ).
                build();

        return developers;
    }
}
