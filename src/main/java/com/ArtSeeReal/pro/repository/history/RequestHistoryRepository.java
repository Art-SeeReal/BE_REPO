package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.RequestHistory;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestHistoryRepository extends JpaRepository<RequestHistory,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
