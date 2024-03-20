package com.ArtSeeReal.pro.repository.jpa.history;

import com.ArtSeeReal.pro.entity.history.SnsHistory;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsHistoryRepository extends JpaRepository<SnsHistory,String>, ModuleRepository {

}
