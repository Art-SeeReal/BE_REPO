package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory,String> {

    boolean existsByUid(String uid);
}
