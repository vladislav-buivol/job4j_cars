package ru.job4j.model.adv;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "advertisment_id",
            foreignKey = @ForeignKey(name = "ADV_ID_FK"),
            referencedColumnName = "id", nullable = false)
    private Advertisement adv;

    public Image(Advertisement adv) {
        this.adv = adv;
    }

    public Image() {

    }
}
