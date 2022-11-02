package com.rup.rup_backend.controller;

import com.rup.rup_backend.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public User login(@RequestBody User user){
        // @RequestBody를 param으로 사용
        // Email, Password
        // 이메일 있는지, 있으면 비밀번호 맞는지 확인
        // 카카오톡 로그인은 UID만 확인?

        User loginUser = new User(user.getUid(), user.getEmail(), user.getPassword());

        System.out.println(loginUser);

        return loginUser;

        /** Request(C->S)
         * UID
         * email
         * password
        */

        /** Response(S->C)
         * success: 1
         */
    }

    @PostMapping("/find-pw-before-email")
    public String findPwBeforeEmail(){
        // @RequestBody를 param으로 사용하기
        // Email, College, Major
        // 이메일과 학교, 학과를 동시에 찾음  -> 중복가능성 있음, 좀 더 세분화된 검증 필요할 듯
        // 재설정하나 ?

        /** Request
         * email
         * */

        /** Response
         * UID
         * */

        return "Find Password (Before sending an email)";
    }

    @PostMapping("/find-pw-after-email")
    public String findPwAfterEmail(){

        /** Request
         * UID
         * temp_pw
         * 그 후 user pw = temp_pw
         */

        /** Response
         * success: 1
         * */

        return "Find password (After sending and email)";
    }

    @PostMapping("/add-new-user")
    public String addNewUser(){
        // @RequestBody를 param으로 사용, 받아옴 -> DB
        // 카카오톡 로그인 시 회원가입과 그냥 기본 회원가입의 폼이 같음, 동시에 사용
        /** Request(C->S)
         * UID
         * nickname
         * email
         * password
         * sex
         * birth
         */

        /** Response
         * success: 1
         * */

        return "Add new user";
    }

    @PostMapping("/nickname-check")
    public String nicknameCheck(){

        /** Request
         * nickname
         * */

        /** Response
         * success: 1
         * */

        return "nickname check";
    }

    @GetMapping("/personal-page")
    public String personalPage(){
        // param 없이 요청 오면 Server -> Client
        /**
         * FLOWER_TABLE.Flower, Flower_grown_level where UID = {UID}
         * Flower 받아와서 _ 기준으로 나누기 {색}_{번호}
         */
        
        // home에서 다 받아옴

        return "personal Page";
    }
}
