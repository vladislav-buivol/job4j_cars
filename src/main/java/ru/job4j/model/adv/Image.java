package ru.job4j.model.adv;

import ru.job4j.model.EntityModel;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Image implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisment_id", foreignKey = @ForeignKey(name = "ADV_ID_FK"))
    private Advertisement adv;

    private String contentType;
    private String name;
    private long size;

    private byte[] imgData;

    public Advertisement getAdv() {
        return adv;
    }

    public Image setAdv(Advertisement adv) {
        this.adv = adv;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        Image image = (Image) o;
        return id == image.id
                && Objects.equals(adv, image.adv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adv);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public Image setImgData(byte[] imgData) {
        this.imgData = imgData;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public Image setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getName() {
        return name;
    }

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    public long getSize() {
        return size;
    }

    public Image setSize(long size) {
        this.size = size;
        return this;
    }
}
