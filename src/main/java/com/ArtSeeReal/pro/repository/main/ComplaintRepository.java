package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Complaint;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
