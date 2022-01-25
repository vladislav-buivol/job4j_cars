package ru.job4j.model.role;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "permissions", joinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "action_id", nullable = false, updatable = false)}
    )
    private Set<Action> allowedActions;
}
