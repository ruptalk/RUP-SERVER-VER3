package com.rup.rup_backend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="POINT_RECORD")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PointRecord {
    @Id
    private int id;
    private String uid;
    private String email;
    private String date;
    private int point;
}
