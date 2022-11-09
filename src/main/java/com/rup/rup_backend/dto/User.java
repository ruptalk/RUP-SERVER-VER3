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

    public User(String UID, String Email, String Password){
        this.uid = UID;
        this.email = Email;
        this.password = Password;
    }
}
