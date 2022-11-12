package com.rup.rup_backend.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeAndPointRecord {
    private String noticeDate;
    private String notice;

    private String uid;
    private String email;
    private List<String> pointRecordDate;
    private List<Integer> point;
}
