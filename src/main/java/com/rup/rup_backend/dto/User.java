package com.rup.rup_backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private String uid;
    private String email;
    private String password;
    private String nickname;
    private String sex;
    private String birth;
    private String college;
    private String major;
    private int point;
    private int countRecycle;

    public User(String uid, String email, String password){
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

}
