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
    private String college;
    private String major;
    private int point;
    private int countRecycle;
    private calendar calendarDate;
    private flower flowerRecord;

    public User(String uid, String email, String password){
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    public User(String uid, String email, String nickname, String password, String sex, String birth, String college, String major, int point, int countRecycle) {
        this.uid = uid;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.college = college;
        this.major = major;
        this.point = point;
        this.countRecycle = countRecycle;
    }

    public User(String uid, String email, String password, String nickname, String sex, String birth, String college, String major, int point, int countRecycle, calendar calendarDate, flower flowerRecord) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.birth = birth;
        this.college = college;
        this.major = major;
        this.point = point;
        this.countRecycle = countRecycle;
        this.calendarDate = calendarDate;
        this.flowerRecord = flowerRecord;
    }
}
