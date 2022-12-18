package com.rup.rup_backend.controller;

import com.rup.rup_backend.dto.Rank;
import com.rup.rup_backend.entity.RankInfo;
import com.rup.rup_backend.repository.RankRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rank")
public class RankController {
    private final RankRepository rankRepo;

    public RankController(RankRepository rankRepo) {
        this.rankRepo = rankRepo;
    }

    @GetMapping("/college-rank")
    public List<Rank> showCollegeRank(){

        List<RankInfo> selectCollegeRank = rankRepo.findRankGroupByCollege();
        List<Rank> collegeRank = selectCollegeRank
                .stream()
                .map(r -> new Rank(r.getRank(), r.getCollege(), r.getTotalPoint()))
                .collect(Collectors.toList());

        return collegeRank;
    }

    @GetMapping("/each-rank")
    public List<Rank> showEachRank(){

        List<RankInfo> selectCollegeRank = rankRepo.findRankAllUID();
        List<Rank> collegeRank = selectCollegeRank
                .stream()
                .map(r -> new Rank(r.getUid(), r.getRank(), r.getCollege(), r.getTotalPoint()))
                .collect(Collectors.toList());

        return collegeRank;

    }
}
