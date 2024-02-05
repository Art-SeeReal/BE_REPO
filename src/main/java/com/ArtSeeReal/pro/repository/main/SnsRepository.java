package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Sns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsRepository extends JpaRepository<Sns,String> {

    boolean existsByUid(String uid);
}
