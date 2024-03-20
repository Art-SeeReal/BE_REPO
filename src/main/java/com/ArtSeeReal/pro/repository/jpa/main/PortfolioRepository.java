package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.Portfolio;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> , ModuleRepository {
    Page<Portfolio> findAllByOrderByRegDateDesc(Pageable pageable);
    Page<Portfolio> findByRegionTypeOrderByRegDateDesc(RegionType regionType, Pageable pageable);
    Page<Portfolio> findByTitleContainingOrderByRegDateDesc(String title, Pageable pageable);
}
