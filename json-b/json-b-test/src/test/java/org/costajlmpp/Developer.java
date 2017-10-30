package org.costajlmpp;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.json.bind.config.PropertyOrderStrategy;
import java.util.HashMap;
import java.util.Map;


@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Developer {
    public static enum Gender { M, F }

    @javax.json.bind.annotation.JsonbProperty("first-name")
    private String name;


   // @JsonbTypeAdapter(GenederAdapter.class)
    private Gender gender;
    private Map<String, String> phones = new HashMap<>();


    public Developer(String name, Gender gender, Map<String, String> phones) {
        this.name = name;
        this.gender = gender;
        this.phones = phones;
    }

    public Developer() {}

    public Gender getGender() {
        return gender;
    }


    public String getName() {
        return name;
    }

    public Map<String, String> getPhones() {
        return phones;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhones(Map<String, String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", phones=" + phones +
                '}';
    }
}
