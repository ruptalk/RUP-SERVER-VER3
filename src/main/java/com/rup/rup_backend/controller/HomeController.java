package com.rup.rup_backend.controller;

import com.rup.rup_backend.dto.GetPointRecord;
import com.rup.rup_backend.dto.NoticeAndPointRecord;
import com.rup.rup_backend.dto.Success;
import com.rup.rup_backend.dto.User;
import com.rup.rup_backend.entity.Notice;
import com.rup.rup_backend.entity.PointRecord;
import com.rup.rup_backend.entity.UserInfo;
import com.rup.rup_backend.repository.NoticeRepository;
import com.rup.rup_backend.repository.PointRecordRepository;
import com.rup.rup_backend.repository.UserInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final UserInfoRepository uIRepo;
    private final PointRecordRepository pRRepo;
    private final NoticeRepository noticeRepo;

    public HomeController(UserInfoRepository uIRepo, PointRecordRepository pRRepo, NoticeRepository noticeRepo) {
        this.uIRepo = uIRepo;
        this.pRRepo = pRRepo;
        this.noticeRepo = noticeRepo;
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


        String uid = user.getUid();

        User userInfo = new User();


        
        return userInfo;
    }

    @GetMapping("/notice-and-point-record")
    public NoticeAndPointRecord noticeAndRecord(@RequestBody User user){
        // param 받아오는 것 없이 요청 오면 Server -> Client 전달. JSON

        GetPointRecord pointRecord = new GetPointRecord(user.getUid());
        NoticeAndPointRecord returnNoticeandPR = new NoticeAndPointRecord();
        /** Request
         * UID
         * */

        /** Response
         * UID
         * notice
         * pointRecordDate: [] (limit 20)
         * */

//        List<Notice> notice = noticeRepo.

        return returnNoticeandPR;
    }

    @PostMapping("/get-point-record")
    public Success InsertPointRecord(@RequestBody GetPointRecord getPointRecord) {
        String uid = getPointRecord.getUid();
//        String insertDate = getPointRecord.getInsertDate();
        int point = getPointRecord.getPoint();

        Optional<UserInfo> findUid = uIRepo.findById(uid);
        Success returnSuccess = new Success();

        if (!findUid.isEmpty()) {
            // uid에 맞는 email 있으면
            String userEmail = findUid.get().getEmail();

            PointRecord pr = PointRecord.builder()
                    .uid(uid)
                    .email(userEmail)
                    .point(point)
                    .build();
            pRRepo.save(pr);
            returnSuccess.setSuccess(true);

        } else {
            returnSuccess.setSuccess(false);
        }
        return returnSuccess;

    }
}
