package com.rup.rup_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ReturnOnlyUid {
    private String uid;

    public ReturnOnlyUid(String uid){
        this.uid = uid;
    }

    // 비밀번호 찾기 시 이용 (메일을 보내기 위한 유저가 존재하는지 확인 할 때)
}
