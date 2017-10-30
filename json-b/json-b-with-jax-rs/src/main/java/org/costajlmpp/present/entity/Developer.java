package org.costajlmpp.present.entity;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Developer {

    @JsonbProperty("firstName")
    private String name;
    private int age;

    public Developer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toJson() {

        // by default the private fields will be ignored
        // we need privacity configurations
        //JsonbBuilder jsonbBuilder1 = JsonbProvider.provider().create().withConfig(config);
        // instead of
        //JsonbBuilder jsonbBuilder = javax.json.bind.spi.JsonbProvider.provider().create();

        JsonbConfig config = new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {

            // NOTES: note that we can get all metadata from the fields or methods, this allows us to use custom annotations.

            @Override
            public boolean isVisible(Field field) {
                return true;
            }

            @Override
            public boolean isVisible(Method method) {
                return false;
            }
        });
        //JsonbBuilder jsonbBuilder1 = JsonbProvider.provider().create().withConfig(config);

        /*
        JsonbBuilder jsonbBuilder = JsonbProvider.provider().create();
        Jsonb jsonb = jsonbBuilder.build();
        String s = jsonb.toJson(this);
         */


        // shortput
        //Jsonb jsonb1 = JsonbBuilder.create();
        // but for using the configuration
        //Jsonb jsonb1 = JsonbBuilder.create(config);
        //jsonb1.toJson(this);

        return JsonbBuilder.newBuilder().withConfig(config).build().toJson(this);
    }
}
