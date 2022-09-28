package com.rup.rup_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @PostMapping("/login")
    public String isExistUser(){
        // @RequestBody를 param으로 사용
        // Email, Password
        // 이메일 있는지, 있으면 비밀번호 맞는지 확인
        // 카카오톡 로그인은 UID만 확인?
        return "Check isExistUser";
    }

    @PostMapping("/find-pw")
    public String findPW(){
        // @RequestBody를 param으로 사용하기
        // Email, College, Major
        // 이메일과 학교, 학과를 동시에 찾음  -> 중복가능성 있음, 좀 더 세분화된 검증 필요할 듯

        return "Find Password";
    }

    @PostMapping("/add-new-user")
    public String addNewUser(){
        // @RequestBody를 param으로 사용, 받아옴 -> DB
        // 카카오톡 로그인 시 회원가입과 그냥 기본 회원가입의 폼이 같음, 동시에 사용
        /**
         * Nickname
         * Email
         * Password
         * College
         * Major
         */

        return "Add new user";
    }

    @GetMapping("/personal-page")
    public String personalPage(){
        // param 없이 요청 오면 Server -> Client
        /**
         * FLOWER_TABLE.Flower, Flower_grown_level where UID = {UID}
         * Flower 받아와서 _ 기준으로 나누기 {색}_{번호}
         */

        return "personal Page";
    }
}
