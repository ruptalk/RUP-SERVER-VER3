package com.rup.rup_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="FLOWER_TABLE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class FlowerInfo {
    @Id
    private int id;
    private String uid;
    private String flower;
    private String flowerNickname;
    private int flowerGrownLevel;
    private String date;
}
