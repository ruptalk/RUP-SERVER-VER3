package com.rup.rup_backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    private String uid;
    private String flower;
    private String flowerNickname;
    private int flowerGrownLevel;
    private String date;
}
