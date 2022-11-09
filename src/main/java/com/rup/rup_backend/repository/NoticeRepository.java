package com.rup.rup_backend.repository;

import com.rup.rup_backend.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
