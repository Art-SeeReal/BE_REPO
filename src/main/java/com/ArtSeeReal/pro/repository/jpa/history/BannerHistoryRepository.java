package com.ArtSeeReal.pro.repository.jpa.history;

import com.ArtSeeReal.pro.entity.history.BannerHistory;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerHistoryRepository extends JpaRepository<BannerHistory,String>, ModuleRepository {

}
