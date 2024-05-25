package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.composite.FavoriteRecruitmentKey;
import com.ArtSeeReal.pro.entity.main.FavoriteRecruitments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRecruitmentsRepository extends JpaRepository<FavoriteRecruitments, FavoriteRecruitmentKey> {
    @Query("SELECT ul.pk.recruitmentUid FROM FAVORITE_RECRUITMENT ul WHERE ul.pk.userUid = :userUid")
    List<String> findRecruitmentUidByUserUid(@Param("userUid") String userUid);
}
