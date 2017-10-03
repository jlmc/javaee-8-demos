package org.jcosta;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.UUID;

public class Purchase {

    private String id;

    private String description = "";

    private BigDecimal value = BigDecimal.ZERO;

    private LinkedList<Detail> details = new LinkedList<>();

    public Purchase(String description, BigDecimal value) {
        this.id = UUID.randomUUID().toString().toUpperCase();
        this.description = description;
        this.value = value;
    }

    public void addValue(String description,  BigDecimal value) {
        this.value = this.value.add(value);
        this.details.add(new Detail(description, value));
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    static class Detail {
        final String description;
        final BigDecimal value;

        private Detail(String description, BigDecimal value) {

            this.description = description;
            this.value = value;
        }
    }
}
