package com.rup.rup_backend.repository;

import com.rup.rup_backend.entity.Notice;
import com.rup.rup_backend.entity.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface PointRecordRepository extends JpaRepository<PointRecord, Integer> {
    @Query(value = "SELECT * FROM POINT_RECORD P WHERE P.UID = :uid ORDER BY Date DESC LIMIT 20", nativeQuery = true)
    List<PointRecord> findPointRecord(@Param("uid")String uid);

    @Query(value = "SELECT id, UID, Email, date_format(Date, '%Y-%m-%d') AS Date, SUM(Point) as Point FROM POINT_RECORD WHERE UID = :uid GROUP BY (date_format(Date, '%Y-%m-%d')) ORDER BY Date DESC", nativeQuery = true)
    List<PointRecord> findCalendarDate(@Param("uid")String uid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO POINT_RECORD(UID, Email, Point) VALUES (:uid, :email, :point)", nativeQuery = true)
    void insertPointRecord(@Param("uid")String uid, @Param("email")String email, @Param("point")int point);
}
