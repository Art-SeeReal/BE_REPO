package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.RequestCommentHistory;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCommentHistoryRepository extends JpaRepository<RequestCommentHistory,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
