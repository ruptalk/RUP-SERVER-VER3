package com.rup.rup_backend.repository;

import com.rup.rup_backend.entity.PointRecord;
import com.rup.rup_backend.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    // <[Entity], [Entity.PK]>
    Optional<UserInfo> findByNickname(String Nickname);
    Optional<UserInfo> findByEmailAndPassword(String Email, String Password);
    Optional<UserInfo> findByEmailAndCollegeAndMajor(String Email, String College, String Major);
}

