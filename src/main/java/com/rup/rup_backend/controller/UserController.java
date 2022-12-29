package com.rup.rup_backend.controller;

import com.rup.rup_backend.dto.ReturnOnlyUid;
import com.rup.rup_backend.dto.Success;
import com.rup.rup_backend.dto.User;
import com.rup.rup_backend.entity.UserInfo;
import com.rup.rup_backend.repository.UserInfoRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserInfoRepository uIRepo;

    public UserController(UserInfoRepository uIRepo) {
        this.uIRepo = uIRepo;
    }

    @Value("${custom.path.user.user-images-path}")
    private String userImagePath;

    @PostMapping("/login")
    public ReturnOnlyUid login(@RequestBody User user) {

        String uid = user.getUid();
        String email = user.getEmail();
        String pw = user.getPassword();

        ReturnOnlyUid returnSuccess = new ReturnOnlyUid();

        if (uid == null) {
            // 이메일 로그인이면 (uid가 없음)
            Optional<UserInfo> loginInfo = uIRepo.findByEmailAndPassword(email, pw);
            if (loginInfo.isEmpty()) {
                // 로그인 정보 일치 X
                returnSuccess.setUid("-1");
            } else if (loginInfo.get().getEmail().equals(email) && Objects.equals(loginInfo.get().getPassword(), pw)) {
                returnSuccess.setUid(loginInfo.get().getUid());
            }
        } else {
            // 카카오톡 로그인이면
            Optional<UserInfo> loginInfo = uIRepo.findById(uid);
            if (loginInfo.isPresent() && loginInfo.get().getUid().equals(uid)) {
                returnSuccess.setUid(loginInfo.get().getUid());
            } else {
                returnSuccess.setUid("-1");
            }
        }

        return returnSuccess;
    }

    @PostMapping("/find-pw-before-email")
    public ReturnOnlyUid findPwBeforeEmail(@RequestBody User user) {

        String email = user.getEmail();
        String college = user.getCollege();
        String major = user.getMajor();

        Optional<UserInfo> findUid = uIRepo.findByEmailAndCollegeAndMajor(email, college, major);
        ReturnOnlyUid returnUid = null;

        if (findUid.isPresent()) {
            // Email && College && Major 동시에 만족하는 정보 있으면
            returnUid = new ReturnOnlyUid(findUid.get().getUid());
        } else {
            returnUid = new ReturnOnlyUid("-1");
        }
        return returnUid;
    }

    @PostMapping("/find-pw-after-email")
    public Success findPwAfterEmail(@RequestBody User user) throws Exception {

        String uid = user.getUid();
        String temp_pw = user.getTempPw();

        Success returnSuccess = new Success();

        try{
            uIRepo.updateTemporaryPassword(temp_pw, uid);
            returnSuccess.setSuccess(true);
        }
        catch(Exception e){
            returnSuccess.setSuccess(false);
        }

        return returnSuccess;
    }

    @PostMapping("/add-new-user")
    public Success addNewUser(@RequestBody User user) {

        String UID = user.getUid();
        Success returnSuccess = new Success();

        if (uIRepo.findById(UID).isPresent()) {
            // UID 중복 확인하기 - 이미 존재하면
            returnSuccess.setSuccess(false);
        } else {
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
    public Success nicknameCheck(@RequestBody User nicknameCheck) {

        String nickname = nicknameCheck.getNickname();

        Success returnSuccess = new Success();

        if (uIRepo.findByNickname(nickname).isEmpty()) {
            // 이미 같은 닉네임 존재
            returnSuccess.setSuccess(true);
        } else {
            returnSuccess.setSuccess(false);
        }

        return returnSuccess;
    }

    @PostMapping("/update-user-info")
    public Success updateUserInfo(@RequestPart(value = "userInfo") User userInfo, @RequestPart(value = "profilePhoto") MultipartFile profilePhoto) throws Exception {

        Success returnSuccess = new Success();

        try{
            if(!profilePhoto.isEmpty()){
                File savePhoto = new File(userImagePath, userInfo.getUid()+".jpg");
                profilePhoto.transferTo(savePhoto);
            }

            String uid = userInfo.getUid();
            String email = userInfo.getEmail();
            String password = userInfo.getPassword();
            String nickname = userInfo.getNickname();
            String sex = userInfo.getSex();
            String birth = userInfo.getBirth();
            String college = userInfo.getCollege();
            String major = userInfo.getMajor();

            uIRepo.updateUserInfo(email, password, nickname, sex, birth, college, major, uid);

            returnSuccess.setSuccess(true);
        }
        catch (Exception e){
            returnSuccess.setSuccess(false);
        }

        return returnSuccess;
    }

    @PostMapping("/delete-user-info")
    public Success deleteUser(@RequestBody User user) {

        String uid = user.getUid();

        Success returnSuccess = new Success();

        try{
            if(uid != null){
                uIRepo.deleteUserInfo(uid);
                returnSuccess.setSuccess(true);
            }
        }
        catch(Exception e){
            returnSuccess.setSuccess(false);
        }

        return returnSuccess;
    }
}