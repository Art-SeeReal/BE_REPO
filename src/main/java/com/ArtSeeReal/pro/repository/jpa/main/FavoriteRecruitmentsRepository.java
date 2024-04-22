package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.composite.FavoriteRecruitmentKey;
import com.ArtSeeReal.pro.entity.main.FavoriteRecruitments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRecruitmentsRepository extends JpaRepository<FavoriteRecruitments, FavoriteRecruitmentKey> {
}
