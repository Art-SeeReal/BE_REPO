package com.ArtSeeReal.pro.repository.history;

import com.ArtSeeReal.pro.entity.history.UserHistory;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory,String>, ModuleRepository {

}
