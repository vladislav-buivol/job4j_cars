package ru.job4j.model.adv;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "advertisment_id", foreignKey = @ForeignKey(name = "ADV_ID_FK"))
    private Advertisement adv;

}
