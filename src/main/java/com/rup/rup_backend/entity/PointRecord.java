package com.rup.rup_backend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="POINT_RECORD")
@NoArgsConstructor
@Getter
@Data
@ToString
public class PointRecord {
    @Id
    private String uid;
    private String email;
    private String date;
    private int point;

    @Builder
    public PointRecord(String uid, String email, int point) {
        this.uid = uid;
        this.email = email;
        this.point = point;
    }
    @Builder
    public PointRecord(String uid){
        this.uid = uid;
    }


}
