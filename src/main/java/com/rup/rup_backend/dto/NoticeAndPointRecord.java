package com.rup.rup_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class NoticeAndPointRecord {
    private String noticeDate;
    private String notice;

    private String uid;
    private String email;
    private List<String> pointRecordDate;
    private List<Integer> point;
}
