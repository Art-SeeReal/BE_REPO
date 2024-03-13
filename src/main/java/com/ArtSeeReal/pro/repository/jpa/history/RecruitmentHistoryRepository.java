package com.ArtSeeReal.pro.repository.jpa.history;

import com.ArtSeeReal.pro.entity.history.RecruitmentHistory;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentHistoryRepository extends JpaRepository<RecruitmentHistory,String>, ModuleRepository {

}
