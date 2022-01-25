package ru.job4j.model.adv;

import ru.job4j.model.car.Car;
import ru.job4j.model.user.Account;

import javax.persistence.*;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "ACCOUNT_ID_FK"))
    private Account account;

    @OneToOne
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"))
    private Car car;
}
