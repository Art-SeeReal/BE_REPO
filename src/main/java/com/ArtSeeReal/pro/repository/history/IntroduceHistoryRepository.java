package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.IntroduceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroduceHistoryRepository extends JpaRepository<IntroduceHistory,String> {

    boolean existsByUid(String uid);
}
