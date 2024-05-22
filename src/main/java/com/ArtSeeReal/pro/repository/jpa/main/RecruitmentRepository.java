package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment,String>, ModuleRepository {
    Page<Recruitment> findAllByOrderByRegDateDesc(Pageable pageable);
    Page<Recruitment> findByTitleContainingOrderByRegDateDesc(String title, Pageable pageable);
    List<String> findUidByDueDateAfter(LocalDateTime oneWeekAgo);
}
