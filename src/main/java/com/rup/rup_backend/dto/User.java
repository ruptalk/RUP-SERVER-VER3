package com.rup.rup_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class User {
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
    private List<String> calendarDate;
    private List<String> flower;

    public User(String uid, String email, String password){
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    public User(String uid, String email, String password, String nickname, String sex, String birth, String college, String major, int point, int countRecycle) {
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
    }
}
