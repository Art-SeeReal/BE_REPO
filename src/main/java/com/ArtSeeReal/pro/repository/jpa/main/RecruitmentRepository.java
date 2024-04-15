package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecruitmentRepository extends JpaRepository<Recruitment,String>, ModuleRepository {
    @Transactional
    @Modifying
    @Query("UPDATE RECRUITMENT_TB r SET r.viewCnt = r.viewCnt + 1 WHERE r.uid = :uid")
    void incrementViewCnt(String uid);
    List<String> findUidByDueDateAfter(LocalDateTime oneWeekAgo);
}
