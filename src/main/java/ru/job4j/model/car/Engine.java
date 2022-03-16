package ru.job4j.model.car;

import ru.job4j.model.EntityModel;

import javax.persistence.*;

@Entity
@Table(name = "engine")
public class Engine implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Engine(String name) {
        this.name = name;
    }

    public Engine() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
