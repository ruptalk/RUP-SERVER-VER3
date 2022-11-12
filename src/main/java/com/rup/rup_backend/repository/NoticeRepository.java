package com.rup.rup_backend.repository;

import com.rup.rup_backend.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    @Query(value = "SELECT * FROM NOTICE ORDER BY Date DESC Limit 1", nativeQuery = true)
    Optional<Notice> findNoticeByMaxDate();
}
