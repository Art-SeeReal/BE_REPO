package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.RequestCommentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCommentHistoryRepository extends JpaRepository<RequestCommentHistory,String> {

    boolean existsByUid(String uid);
}
