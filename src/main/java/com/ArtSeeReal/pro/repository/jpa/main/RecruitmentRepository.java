package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment,String>, ModuleRepository {
    Page<Recruitment> findAllByOrderByRegDateDesc(Pageable pageable);
    Page<Recruitment> findByTitleContainingOrderByRegDateDesc(String title, Pageable pageable);
    List<String> findUidByDueDateAfter(LocalDateTime oneWeekAgo);
}
