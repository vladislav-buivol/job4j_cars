package ru.job4j.model.adv;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisment_id", foreignKey =  @ForeignKey(name = "ADV_ID_FK"))
    private Advertisement adv;

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
}
