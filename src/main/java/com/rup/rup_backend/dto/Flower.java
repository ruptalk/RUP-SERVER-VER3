package com.rup.rup_backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    private int id;
    private String uid;
    private String kindOfFlower;
    private String flowerNickname;
    private int flowerGrownLevel;
    private String date;

    public Flower(String uid, String kindOfFlower, String flowerNickname, int flowerGrownLevel, String date) {
        this.uid = uid;
        this.kindOfFlower = kindOfFlower;
        this.flowerNickname = flowerNickname;
        this.flowerGrownLevel = flowerGrownLevel;
        this.date = date;
    }
}
