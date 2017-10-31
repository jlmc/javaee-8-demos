package org.costajlmpp.account.entity;

import javax.json.bind.annotation.*;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.json.bind.annotation.JsonbDateFormat.TIME_IN_MILLIS;

/**
 * @author costa
 * on 29/09/2017.
 */
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@JsonbNillable(false)
@Entity
@Table(name = "t_moviment")
public class Moviment {

    @Id
    @GeneratedValue(generator="uuid")
    private String id;

    private BigDecimal value;

    @JsonbDateFormat(TIME_IN_MILLIS)
    private LocalDateTime time;

    public String getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
