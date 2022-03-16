package ru.job4j.model.adv;

import ru.job4j.model.EntityModel;
import ru.job4j.model.car.Car;
import ru.job4j.model.user.Account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Advertisement implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean status = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "ACCOUNT_ID_FK"))
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"))
    private Car car;

    @OneToMany(
            mappedBy = "adv",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Image> images = new HashSet<>();

    private BigDecimal price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Advertisement(Account account, Car car) {
        this.account = account;
        this.car = car;
    }

    public Advertisement() {

    }

    @PrePersist
    protected void onCreate() {
        created = new Date(System.currentTimeMillis());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Advertisement setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Advertisement setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Advertisement setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Advertisement setCar(Car car) {
        this.car = car;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public Advertisement setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Advertisement setImages(Set<Image> images) {
        this.images = images;
        return this;
    }

    public void addImage(Image img) {
        images.add(img);
        img.setAdv(this);
    }

    public void removeImage(Image img) {
        images.remove(img);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Advertisement setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
