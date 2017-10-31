package org.costajlmpp.account.entity;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.util.*;

@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@JsonbNillable(false)
@Entity
@Table(name = "t_account")
@NamedQuery(name = Account.FIND, query = "select distinct a from Account a left join fetch a.moviments")
@NamedQuery(name = Account.FIND_BY_ID, query = "select distinct a from Account a left join fetch a.moviments where a.id = :id")
public class Account {

    public static final String FIND = "org.costajlmpp.account.entity.Account.find";
    public static final String FIND_BY_ID = "org.costajlmpp.account.entity.Account.find_by_id";

    @Id
    @GeneratedValue(generator="uuid")
    private String id;
    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "t_account_id")
    @OrderColumn(name = "time")
    private List<Moviment> moviments = new ArrayList<>(0);

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Moviment> getMoviments() {
        return Collections.unmodifiableList(moviments);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMoviments(List<Moviment> moviments) {
        this.moviments = moviments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMoviment(Moviment moviment) {
        this.moviments.add(moviment);
    }
}
