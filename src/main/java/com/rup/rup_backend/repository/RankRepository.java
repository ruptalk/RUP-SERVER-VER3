package com.rup.rup_backend.repository;

import com.rup.rup_backend.entity.RankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankRepository extends JpaRepository<RankInfo, String> {
   @Query(value="SELECT UID, RANK() OVER (ORDER BY(TotalPoint) DESC) AS Rank, College, SUM(Point) AS TotalPoint FROM USER_INFO GROUP BY(College) ORDER BY(Rank)", nativeQuery = true)
   List<RankInfo> findRankGroupByCollege();

   @Query(value="SELECT UID, RANK() OVER (ORDER BY(Point) DESC) AS Rank, College, Point AS TotalPoint FROM USER_INFO ORDER BY(Rank)", nativeQuery = true)
   List<RankInfo> findRankAllUID();
}
