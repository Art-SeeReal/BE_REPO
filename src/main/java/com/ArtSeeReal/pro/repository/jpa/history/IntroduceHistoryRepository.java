package com.ArtSeeReal.pro.repository.jpa.history;

import com.ArtSeeReal.pro.entity.history.IntroduceHistory;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroduceHistoryRepository extends JpaRepository<IntroduceHistory,String>, ModuleRepository {

}
