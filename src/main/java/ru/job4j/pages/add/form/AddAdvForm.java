package ru.job4j.pages.add.form;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class AddAdvForm {
    private boolean isUsed;
    private String model;
    private BigDecimal price;
    private BigDecimal mileage;
    private int nrOfSeats;
    private String engineSize;
    private String fuel;
    private String gearBox;
    private Set<ImgData> images = new HashSet<>();

    public void setData(String fieldName, String value) {
        switch (fieldName) {
            case "isUsed":
                setUsed(Boolean.parseBoolean(value));
                break;
            case "model":
                setModel(value);
                break;
            case "price":
                setPrice(new BigDecimal(value));
                break;
            case "mileage":
                setMileage(new BigDecimal(value));
                break;
            case "numberOfSeats":
                setNrOfSeats(Integer.parseInt(value));
                break;
            case "engine":
                setEngineSize(value);
                break;
            case "gearbox":
                setGearBox(value);
                break;
            case "fuel":
                setFuel(value);
                break;
            default:
                throw new RuntimeException();
        }
    }

    public void addImg(ImgData image) {
        images.add(image);
    }

    public AddAdvForm setUsed(boolean used) {
        isUsed = used;
        return this;
    }

    public AddAdvForm setModel(String model) {
        this.model = model;
        return this;
    }

    public AddAdvForm setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public AddAdvForm setMileage(BigDecimal mileage) {
        this.mileage = mileage;
        return this;
    }

    public AddAdvForm setNrOfSeats(int nrOfSeats) {
        this.nrOfSeats = nrOfSeats;
        return this;
    }

    public AddAdvForm setEngineSize(String engineSize) {
        this.engineSize = engineSize;
        return this;
    }

    public AddAdvForm setFuel(String fuel) {
        this.fuel = fuel;
        return this;
    }

    public AddAdvForm setGearBox(String gearBox) {
        this.gearBox = gearBox;
        return this;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public int getNrOfSeats() {
        return nrOfSeats;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public String getFuel() {
        return fuel;
    }

    public String getGearBox() {
        return gearBox;
    }

    public Set<ImgData> getImgData() {
        return images;
    }
}
