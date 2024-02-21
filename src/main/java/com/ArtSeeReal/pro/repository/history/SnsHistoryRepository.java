package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.SnsHistory;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsHistoryRepository extends JpaRepository<SnsHistory,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
