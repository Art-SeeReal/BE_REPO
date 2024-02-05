package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.BannerHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerHistoryRepository extends JpaRepository<BannerHistory,String> {

    boolean existsByUid(String uid);
}
