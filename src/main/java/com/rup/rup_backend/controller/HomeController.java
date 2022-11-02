package com.rup.rup_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/main")
    public String mainPage(){
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
        
        return "Main Page";
    }

    @GetMapping("/notice-and-point-record")
    public String noticeAndRecord(){
        // param 받아오는 것 없이 요청 오면 Server -> Client 전달. JSON

        /** Request
         * UID
         * */

        /** Response
         * UID
         * notice (limit 20)
         * pointRecordDate: []
         * */

        return "Notice and Record of Points";
    }
}
