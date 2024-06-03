package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.Portfolio;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> , ModuleRepository {
    Page<Portfolio> findAllByOrderByRegDateDesc(Pageable pageable);
    Page<Portfolio> findByTitleContainingOrderByRegDateDesc(String title, Pageable pageable);
    @Transactional
    @Modifying
    @Query("UPDATE PORTFOLIO_TB p SET p.viewCnt = p.viewCnt + 1 WHERE p.uid = :uid")
    void incrementViewCnt(String uid);
}
