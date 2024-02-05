package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.BoardHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHistoryRepository extends JpaRepository<BoardHistory,String> {

    boolean existsByUid(String uid);
}
