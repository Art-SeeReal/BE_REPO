package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Request;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
