package ru.job4j.model.car;

import ru.job4j.model.EntityModel;

import javax.persistence.*;

@Entity
@Table(name = "driver")
public class Driver implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
