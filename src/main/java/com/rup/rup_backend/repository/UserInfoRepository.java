package com.rup.rup_backend.repository;

import com.rup.rup_backend.entity.PointRecord;
import com.rup.rup_backend.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    // <[Entity], [Entity.PK]>
    Optional<UserInfo> findByNickname(String Nickname);
    Optional<UserInfo> findByEmailAndPassword(String Email, String Password);
    Optional<UserInfo> findByEmailAndCollegeAndMajor(String Email, String College, String Major);

    @Transactional
    @Modifying
    @Query(value = "UPDATE USER_INFO U SET U.point = U.point + :point, U.Count_recycle = U.Count_recycle + :point WHERE U.UID = :uid", nativeQuery = true)
    void updateTotalPointAndRecycle(@Param("point")int point, @Param("uid")String uid) throws Exception;
}

