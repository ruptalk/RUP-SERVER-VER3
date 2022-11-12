package com.rup.rup_backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetPointRecord {
    public String uid;
    public String insertDate;
    public int point;

    public GetPointRecord(String uid){
        this.uid = uid;
    }
}
