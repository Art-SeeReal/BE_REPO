package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.composite.ApplyRecruitmentKey;
import com.ArtSeeReal.pro.entity.main.ApplyRecruitments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRecruitmentsRepository extends JpaRepository<ApplyRecruitments, ApplyRecruitmentKey> {
}
