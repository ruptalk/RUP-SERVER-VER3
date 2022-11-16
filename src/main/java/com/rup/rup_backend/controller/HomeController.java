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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/main")
    public User mainPage(@RequestBody User user){
        // param 받아오는 것 없이 요청 오면 Server -> Client 전달. JSON

        /** Request
         * UID
         * */

        /** Response
         * UID
         * nickname
         * email
         * password
         * sex
         * birth
         * college
         * major
         * recycleCount
         * point
         * calendarDate: []
         * flowerInfo: []
         * */

        String requestUid = user.getUid();

        Optional<UserInfo> findUserInfo = uIRepo.findById(requestUid);
        User returnUser;

        if(findUserInfo.isPresent()){
            // 유저 정보가 존재하면
            String uid = findUserInfo.get().getUid();
            String email = findUserInfo.get().getEmail();
            String nickname = findUserInfo.get().getNickname();
            String password = findUserInfo.get().getPassword();
            String sex = findUserInfo.get().getSex();
            String birth = findUserInfo.get().getBirth();
//            String profile_photo_url = findUserInfo.get().getProfile_photo_url();
            String college = findUserInfo.get().getCollege();
            String major = findUserInfo.get().getMajor();
            int point = findUserInfo.get().getPoint();
            int count_recycle = findUserInfo.get().getCount_recycle();

            List<PointRecord> findRecycledDate = pRRepo.findCalendarDate(requestUid);
            List<GetPointRecord> calendarDate = findRecycledDate
                    .stream()
                    .map(c -> new GetPointRecord(c.getUid(), c.getDate(), c.getPoint()))
                    .collect(Collectors.toList());

            List<FlowerInfo> findFlowerInfo = flowerRepo.findFlowerInfoByUid(requestUid);
            List<Flower> flowers = findFlowerInfo
                    .stream()
                    .map(f -> new Flower(f.getUid(), f.getKindOfFlower(), f.getFlowerNickname(), f.getFlowerGrownLevel(), f.getDate()))
                    .collect(Collectors.toList());

            returnUser = new User(
                    uid,
                    email,
                    nickname,
                    password,
                    sex,
                    birth,
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
    public NoticeAndPointRecord noticeAndRecord(@RequestBody User user){

        String uid = user.getUid();

        /** Request
         * uid
         * */

        /** Response
         * uid
         * email
         * notice
         * pointRecordDate: [] (limit 20)
         * */

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

            System.out.println("get-point-record: " + getPointRecord.toString());

            uIRepo.updateTotalPointAndRecycle(point, uid);

            findUid = uIRepo.findById(uid);

            System.out.println("after-plus-point" + findUid.toString());

            returnSuccess.setSuccess(true);

        } else {
            returnSuccess.setSuccess(false);
        }
        return returnSuccess;

    }
}
