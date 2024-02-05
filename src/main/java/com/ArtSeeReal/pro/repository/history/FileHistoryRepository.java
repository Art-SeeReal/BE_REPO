package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.FileHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileHistoryRepository extends JpaRepository<FileHistory,String> {

    boolean existsByUid(String uid);
}
