package com.rup.rup_backend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class RankInfo {
    @Id
    private String nickname;
    private int rank;
    private String college;
    private int totalPoint;
}
