package com.ArtSeeReal.pro.repository.jpa.history;

import com.ArtSeeReal.pro.entity.history.PortfolioHistory;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioHistoryRepository extends JpaRepository<PortfolioHistory,String>, ModuleRepository {

}
