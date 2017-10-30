package org.jcosta.user.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    private String email;
    private String name;

    @ElementCollection
    @CollectionTable(name = "t_roles", joinColumns = @JoinColumn(name = "email"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>(0);

    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
