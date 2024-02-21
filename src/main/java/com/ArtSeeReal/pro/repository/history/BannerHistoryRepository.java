package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.BannerHistory;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerHistoryRepository extends JpaRepository<BannerHistory,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
