package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint,String> {

    boolean existsByUid(String uid);
}
