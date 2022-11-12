package com.rup.rup_backend.controller;

import com.rup.rup_backend.dto.ReturnOnlyUid;
import com.rup.rup_backend.dto.Success;
import com.rup.rup_backend.dto.User;
import com.rup.rup_backend.entity.UserInfo;
import com.rup.rup_backend.repository.UserInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserInfoRepository uIRepo;

    public UserController(UserInfoRepository uIRepo) {
        this.uIRepo = uIRepo;
    }

    @PostMapping("/login") // ㅇ
    public Success login(@RequestBody User user){
        // @RequestBody를 param으로 사용
        // Email, Password
        // 이메일 있는지, 있으면 비밀번호 맞는지 확인
        // 카카오톡 로그인은 UID만 확인

        String uid = user.getUid();
        String email = user.getEmail();
        String pw = user.getPassword();
        System.out.println(user.toString());

        Success returnSuccess = new Success();
        
        if(uid == null){
            // 이메일 로그인이면 (uid가 없음)
            Optional<UserInfo> loginInfo = uIRepo.findByEmailAndPassword(email, pw);
            System.out.println(loginInfo);
            if(loginInfo.isEmpty()){
                // 로그인 정보 일치 X
                System.out.println("이메일 로그인 - 로그인 정보 일치 X");
                returnSuccess.setSuccess(false);
            }
            else if(loginInfo.get().getEmail() == email && loginInfo.get().getPassword() == pw){
                System.out.println("이메일 로그인 - 로그인 정보 일치 O");
                returnSuccess.setSuccess(true);
            }
        }
        else{
            // 카카오톡 로그인이면
            Optional<UserInfo> loginInfo = uIRepo.findById(uid);
            if(loginInfo.isPresent() && loginInfo.get().getUid() == uid){
                System.out.println("카카오톡 로그인 - 아이디 존재 O");
                System.out.println(uid);
                returnSuccess.setSuccess(true);
            }
            else{
                System.out.println("카카오톡 로그인 - 아이디 존재 X");
                returnSuccess.setSuccess(false);
            }
        }

        return returnSuccess;

        /** Request(C->S)
         * uid
         * email
         * password
        */

        /** Response(S->C)
         * success: 1
         */
    }

    @PostMapping("/find-pw-before-email") // ㅇ
    public ReturnOnlyUid findPwBeforeEmail(@RequestBody User user){
        // @RequestBody를 param으로 사용하기
        // Email, College, Major
        // 이메일과 학교, 학과를 동시에 찾음  -> 중복가능성 있음, 좀 더 세분화된 검증 필요할 듯
        // 재설정하나 ?

        /** Request
         * email
         * college
         * major
         * */

        /** Response
         * UID
         * */

        String email = user.getEmail();
        String college = user.getCollege();
        String major = user.getMajor();

        Optional<UserInfo> findUid = uIRepo.findByEmailAndCollegeAndMajor(email, college, major);
        ReturnOnlyUid returnUid = null;

        if(findUid.isPresent()){
            // Email && College && Major 동시에 만족하는 정보 있으면
            returnUid = new ReturnOnlyUid(findUid.get().getUid());
        }
        else{
            returnUid = new ReturnOnlyUid("-1");
        }
        return returnUid;
    }

    @PostMapping("/find-pw-after-email")
    public Success findPwAfterEmail(@RequestBody User user){

        /** Request
         * UID
         * temp_pw
         * 그 후 user pw = temp_pw
         */

        /** Response
         * success: 1
         * */

        String uid = user.getUid();
        String temp_pw = user.getTempPw();
        System.out.println(temp_pw);

        // UPDATE

        Success returnSuccess = new Success();

        return returnSuccess;
    }

    @PostMapping("/add-new-user") // ㅇ
    public Success addNewUser(@RequestBody User user){
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
        
        String UID = user.getUid();
        Success returnSuccess = new Success();

        if(uIRepo.findById(UID).isPresent()){
            // UID 중복 확인하기 - 이미 존재하면
            returnSuccess.setSuccess(false);
        }
        else{
            UserInfo userinfo = UserInfo.builder()
                    .uid(user.getUid())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .nickname(user.getNickname())
                    .sex(user.getSex())
                    .birth(user.getBirth())
                    .college(user.getCollege())
                    .major(user.getMajor())
                    .build();
            uIRepo.save(userinfo);
            returnSuccess.setSuccess(true);
        }
        return returnSuccess;
    }

    @PostMapping("/nickname-check")
    public Success nicknameCheck(@RequestBody User nicknameCheck){

        String nickname = nicknameCheck.getNickname();

        Success returnSuccess = new Success();

        if(uIRepo.findByNickname(nickname).isEmpty()){
            // 이미 같은 닉네임 존재
            returnSuccess.setSuccess(true);
        }
        else{
            returnSuccess.setSuccess(false);
        }
        
        return returnSuccess;
    }

    @PostMapping("/update-user-info")
    public String updateUserInfo(){

        return "update-user-info";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestBody User user){

        String uid = user.getUid();

        return "delete user";
    }

//    @GetMapping("/personal-page")
//    public String personalPage(){
//        // param 없이 요청 오면 Server -> Client
//        /**
//         * FLOWER_TABLE.Flower, Flower_grown_level where UID = {UID}
//         * Flower 받아와서 _ 기준으로 나누기 {색}_{번호}
//         */
//
//        // home에서 다 받아옴
//
//        return "personal Page";
//    }
}
