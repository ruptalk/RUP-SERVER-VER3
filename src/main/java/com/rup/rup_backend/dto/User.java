package com.rup.rup_backend.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User<calendar, flower> {
    private String uid;
    private String email;
    private String password;
    private String tempPw;
    private String nickname;
    private String sex;
    private String birth;
    private String profileImgPath;
    private String college;
    private String major;
    private int point;
    private int countRecycle;
    private calendar calendarDate;
    private int nowFlowerSeed;
    private flower flowerRecord;

    public User(String uid, String email, String password){
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    public User(String uid, String email, String password, String nickname, String sex, String birth, String profileImgPath, String college, String major, int point, int countRecycle, calendar calendarDate, int nowFlowerSeed, flower flowerRecord) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.birth = birth;
        this.profileImgPath = profileImgPath;
        this.college = college;
        this.major = major;
        this.point = point;
        this.countRecycle = countRecycle;
        this.calendarDate = calendarDate;
        this.nowFlowerSeed = nowFlowerSeed;
        this.flowerRecord = flowerRecord;
    }
}
