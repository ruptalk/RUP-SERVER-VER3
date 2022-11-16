package com.rup.rup_backend.repository;

import com.rup.rup_backend.entity.FlowerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlowerRepository extends JpaRepository<FlowerInfo, Integer> {
    @Query(value = "SELECT * FROM FLOWER_TABLE WHERE UID = :uid", nativeQuery = true)
    List<FlowerInfo> findFlowerInfoByUid(@Param("uid")String uid);
}
