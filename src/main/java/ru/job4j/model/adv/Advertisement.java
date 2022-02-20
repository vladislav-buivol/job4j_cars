package ru.job4j.model.adv;

import ru.job4j.model.car.Car;
import ru.job4j.model.user.Account;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean status;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void addImage(Image img) {
        images.add(img);
        img.setAdv(this);
    }

    public void removeImage(Image img) {
        images.remove(img);
    }
}
