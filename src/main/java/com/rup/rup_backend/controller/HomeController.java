package com.rup.rup_backend.controller;

import com.rup.rup_backend.dto.*;
import com.rup.rup_backend.entity.FlowerInfo;
import com.rup.rup_backend.entity.Notice;
import com.rup.rup_backend.entity.PointRecord;
import com.rup.rup_backend.entity.UserInfo;
import com.rup.rup_backend.repository.FlowerRepository;
import com.rup.rup_backend.repository.NoticeRepository;
import com.rup.rup_backend.repository.PointRecordRepository;
import com.rup.rup_backend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final UserInfoRepository uIRepo;
    private final PointRecordRepository pRRepo;
    private final NoticeRepository noticeRepo;
    private final FlowerRepository flowerRepo;

    public HomeController(UserInfoRepository uIRepo, PointRecordRepository pRRepo, NoticeRepository noticeRepo, FlowerRepository flowerRepo) {
        this.uIRepo = uIRepo;
        this.pRRepo = pRRepo;
        this.noticeRepo = noticeRepo;
        this.flowerRepo = flowerRepo;
    }

    @Value("${custom.path.server.server-address}")
    private String localPath;

    @Value("${custom.path.user.user-images-path}")
    private String imgPath;

    int maxLevel = 30; // 꽃 최대 성장 정도

    @GetMapping("/main")
    public User mainPage(@RequestParam String uid){

        Optional<UserInfo> findUserInfo = uIRepo.findById(uid);
        User returnUser;

        if(findUserInfo.isPresent()){
            // 유저 정보가 존재하면
            String newUid = findUserInfo.get().getUid();
            String email = findUserInfo.get().getEmail();
            String password = findUserInfo.get().getPassword();
            String nickname = findUserInfo.get().getNickname();
            String sex = findUserInfo.get().getSex();
            String birth = findUserInfo.get().getBirth();
            String college = findUserInfo.get().getCollege();
            String major = findUserInfo.get().getMajor();
            int point = findUserInfo.get().getPoint();
            int count_recycle = findUserInfo.get().getCount_recycle();

            List<PointRecord> findRecycledDate = pRRepo.findCalendarDate(uid);
            List<GetPointRecord> calendarDate = findRecycledDate
                    .stream()
                    .map(c -> new GetPointRecord(c.getUid(), c.getDate(), c.getPoint()))
                    .collect(Collectors.toList());

            List<FlowerInfo> findFlowerInfo = flowerRepo.findFlowerInfoByUid(uid);
            List<Flower> flowers = findFlowerInfo
                    .stream()
                    .map(f -> new Flower(f.getUid(), f.getFlower(), f.getFlower_nickname(), f.getFlower_grown_level(), f.getDate()))
                    .collect(Collectors.toList());

            File isExistPfImg = new File(imgPath + uid + ".jpg");
            String profilePath = "";

            if(isExistPfImg.exists()){
                profilePath = localPath + "/user/user-profile-image/" + uid;
            }

            returnUser = new User(
                    uid,
                    email,
                    password,
                    nickname,
                    sex,
                    birth,
                    profilePath,
                    college,
                    major,
                    point,
                    count_recycle,
                    calendarDate,
                    flowers
            );

        }
        else{
            returnUser = new User("-1", "-1", "-1");
        }

        return returnUser;
    }

    @GetMapping("/notice-and-point-record")
    public NoticeAndPointRecord noticeAndRecord(@RequestParam String uid){

        Optional<Notice> notice = noticeRepo.findNoticeByMaxDate();
        List<PointRecord> findRecords = pRRepo.findPointRecord(uid);

        List<GetPointRecord> records;

        if(!notice.isEmpty()){

            records = findRecords
                    .stream()
                    .map(r -> new GetPointRecord(r.getUid(), r.getDate(), r.getPoint()))
                    .collect(Collectors.toList());

            return new NoticeAndPointRecord(
                    notice.get().getDate(),
                    notice.get().getNotice(),
                    findRecords.get(0).getUid(),
                    findRecords.get(0).getEmail(),
                    records
            );
        }
        else{
            return new NoticeAndPointRecord();
        }
    }

    @PostMapping("/get-point-record")
    public Success InsertPointRecord(@RequestBody GetPointRecord getPointRecord) throws Exception {
        String uid = getPointRecord.getUid();
        int point = getPointRecord.getPoint();

        Optional<UserInfo> findUid = uIRepo.findById(uid);
        Success returnSuccess = new Success();

        if (!findUid.isEmpty()) {
            // uid에 맞는 email 있으면
            String userEmail = findUid.get().getEmail();

            pRRepo.insertPointRecord(uid, userEmail, point);
            uIRepo.updateTotalPointAndRecycle(point, uid);
            flowerRepo.updateFlowerGrownLevel(point, uid, maxLevel);

            returnSuccess.setSuccess(true);

        } else {
            returnSuccess.setSuccess(false);
        }
        return returnSuccess;

    }
}
