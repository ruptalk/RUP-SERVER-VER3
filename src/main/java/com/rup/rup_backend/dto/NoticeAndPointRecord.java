package com.rup.rup_backend.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeAndPointRecord<records> {
    private String noticeDate;
    private String notice;

    private String uid;
    private String email;
    private records pointRecord;
}
