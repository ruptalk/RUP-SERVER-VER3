package com.rup.rup_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/home")
public class HomeController {

    @GetMapping("/main")
    public String mainPage(){
        // param 받아오는 것 없이 요청 오면 Server -> Client 전달. JSON
        /**
         * USER_INFO.Nickname
         * USER_INFO.Count_recycle
         * USER_INFO.Point
         */
        
        return "Main Page";
    }

    @GetMapping("/notice-and-record")
    public String noticeAndRecord(){
        // param 받아오는 것 없이 요청 오면 Server -> Client 전달. JSON
        /**
         * NOTICE.Notice where Date desc limit 1;
         * POINT_RECORD.Date, Point limit 20;
         */
        return "Notice and Record of Points";
    }
}
