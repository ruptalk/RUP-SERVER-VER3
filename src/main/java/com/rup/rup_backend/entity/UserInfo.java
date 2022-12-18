package com.rup.rup_backend.entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="USER_INFO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserInfo {

    @Id
    @NonNull
    private String uid;
    @NonNull
    private String email;
    private String password;
    private String nickname;
    private String sex;
    private String birth;
    private String college;
    private String major;
    private int point;
    private int count_recycle;

    @Builder
    public UserInfo(String uid, String email, String password, String nickname, String sex, String birth, String college, String major){
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.birth = birth;
        this.college = college;
        this.major = major;
    } // 회원가입

}
