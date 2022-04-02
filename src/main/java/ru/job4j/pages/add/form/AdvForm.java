package ru.job4j.pages.add.form;

import org.apache.commons.fileupload.FileItem;
import ru.job4j.model.adv.Advertisement;
import ru.job4j.model.adv.Image;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Engine;
import ru.job4j.model.car.Model;
import ru.job4j.model.user.Account;
import ru.job4j.repository.hql.adv.AdsRepository;
import ru.job4j.repository.hql.car.CarModelRepo;
import ru.job4j.repository.hql.car.EngineRepo;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdvForm {
    private boolean isUsed;
    private boolean isSold = false;
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
            case "sold":
                setSold(true);
                break;
            default:
                throw new RuntimeException();
        }
    }

    public static AdvForm of(List<FileItem> fields)
            throws IOException {
        AdvForm form = new AdvForm();
        try {
            Iterator<FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                return form;
            }
            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    form.setData(fileItem.getFieldName(), fileItem.getString());
                } else {
                    String imgName = fileItem.getName();
                    String contentType = fileItem.getContentType();
                    //resp.setContentType(contentType);
                    long size = fileItem.getSize();
                    ImgData imgData = new ImgData(imgName, contentType, fileItem.get(), size);
                    if (size > 0) {
                        form.addImg(imgData);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form;
    }

    public Advertisement createAdv(HttpSession session, Car car, AdvForm form) {
        Account account = (Account) session.getAttribute("account");
        Advertisement advertisement = new Advertisement(account, car)
                .setPrice(form.getPrice())
                .setStatus(form.isSold());
        for (ImgData imgData : form.getImgData()) {
            Image image = new Image().setImgData(imgData.getData())
                    .setContentType(imgData.getContentType())
                    .setName(imgData.getName())
                    .setSize(imgData.getSize());
            advertisement.addImage(image);
        }
        return advertisement;
    }

    public Advertisement updateAdv(AdvForm form, String id) {
        Advertisement advertisement = AdsRepository.instOf().findById(id);
        Car car = advertisement.getCar()
                .setEngine(EngineRepo.instOf().findById(form.getEngineSize()))
                .setMileage(form.getMileage())
                .setNrOfSeats(form.getNrOfSeats())
                .setGearbox(form.getGearBox())
                .setFuel(form.getFuel())
                .setUsed(form.isUsed());
        advertisement.setPrice(form.getPrice())
                .setStatus(form.isSold())
                .setCar(car);
        for (ImgData imgData : form.getImgData()) {
            Image image = new Image().setImgData(imgData.getData())
                    .setContentType(imgData.getContentType())
                    .setName(imgData.getName())
                    .setSize(imgData.getSize());
            advertisement.addImage(image);
        }
        return advertisement;
    }

    public Car createCar(AdvForm form) {
        Model model = CarModelRepo.instOf().findById(form.getModel());
        Engine engine = EngineRepo.instOf().findById(form.getEngineSize());

        return new Car(model)
                .setEngine(engine)
                .setMileage(form.getMileage())
                .setNrOfSeats(form.getNrOfSeats())
                .setGearbox(form.getGearBox())
                .setFuel(form.getFuel())
                .setUsed(form.isUsed());
    }

    public void addImg(ImgData image) {
        images.add(image);
    }

    public AdvForm setUsed(boolean used) {
        isUsed = used;
        return this;
    }

    public AdvForm setModel(String model) {
        this.model = model;
        return this;
    }

    public AdvForm setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public AdvForm setMileage(BigDecimal mileage) {
        this.mileage = mileage;
        return this;
    }

    public AdvForm setNrOfSeats(int nrOfSeats) {
        this.nrOfSeats = nrOfSeats;
        return this;
    }

    public AdvForm setEngineSize(String engineSize) {
        this.engineSize = engineSize;
        return this;
    }

    public AdvForm setFuel(String fuel) {
        this.fuel = fuel;
        return this;
    }

    public AdvForm setGearBox(String gearBox) {
        this.gearBox = gearBox;
        return this;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
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
