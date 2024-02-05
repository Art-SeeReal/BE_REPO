package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.SnsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsHistoryRepository extends JpaRepository<SnsHistory,String> {

    boolean existsByUid(String uid);
}
