package com.rup.rup_backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rank {
    private String nickname;
    private int rank;
    private String college;
    private int totalPoint;

    public Rank(int rank, String college, int totalPoint){
        this.rank = rank;
        this.college = college;
        this.totalPoint = totalPoint;
    }

}
