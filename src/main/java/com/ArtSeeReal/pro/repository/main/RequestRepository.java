package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,String> {

    boolean existsByUid(String uid);
}
