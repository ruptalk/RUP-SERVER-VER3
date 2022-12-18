package com.rup.rup_backend.repository;

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
    @Query(value = "UPDATE USER_INFO U SET U.point = U.Point + :point, U.Count_recycle = U.Count_recycle + :point WHERE U.UID = :uid", nativeQuery = true)
    void updateTotalPointAndRecycle(@Param("point")int point, @Param("uid")String uid) throws Exception;

    @Transactional
    @Modifying
    @Query(value="UPDATE USER_INFO U SET " +
            "U.Email = :email," +
            "U.Password = :password," +
            "U.Nickname = :nickname," +
            "U.Sex = :sex," +
            "U.Birth = :birth," +
            "U.College = :college," +
            "U.Major = :major" +
            " WHERE U.UID = :uid"
            , nativeQuery = true)
    void updateUserInfo(@Param("email")String email,
                        @Param("password")String password,
                        @Param("nickname")String nickname,
                        @Param("sex")String sex,
                        @Param("birth")String birth,
                        @Param("college")String college,
                        @Param("major")String major,
                        @Param("uid")String uid
                        ) throws Exception;
}

