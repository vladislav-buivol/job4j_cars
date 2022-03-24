package ru.job4j.model.car;

import ru.job4j.model.EntityModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mode_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private Model model;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "history_owner",
            joinColumns = {
                    @JoinColumn(name = "car_id",
                            referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id",
                            referencedColumnName = "id", nullable = false, updatable = false)
            }
    )
    private Set<Driver> drivers = new HashSet<>();

    private BigDecimal mileage;
    private String gearbox;
    private String release;
    private String fuel;
    private int nrOfSeats;
    private boolean isUsed;

    public Car(Model model) {
        this.model = model;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public Car setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public Car setMileage(BigDecimal mileage) {
        this.mileage = mileage;
        return this;
    }

    public Car setGearbox(String gearbox) {
        this.gearbox = gearbox;
        return this;
    }

    public Car setRelease(String release) {
        this.release = release;
        return this;
    }

    public Car setFuel(String fuel) {
        this.fuel = fuel;
        return this;
    }

    public Car setNrOfSeats(int nrOfSeats) {
        this.nrOfSeats = nrOfSeats;
        return this;
    }

    public Car setUsed(boolean used) {
        isUsed = used;
        return this;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public String getGearbox() {
        return gearbox;
    }

    public String getRelease() {
        return release;
    }

    public String getFuel() {
        return fuel;
    }

    public int getNrOfSeats() {
        return nrOfSeats;
    }

    public boolean isUsed() {
        return isUsed;
    }
}