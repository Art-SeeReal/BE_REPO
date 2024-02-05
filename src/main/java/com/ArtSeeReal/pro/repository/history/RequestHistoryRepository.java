package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestHistoryRepository extends JpaRepository<RequestHistory,String> {

    boolean existsByUid(String uid);
}
