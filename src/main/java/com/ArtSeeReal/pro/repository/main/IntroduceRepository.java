package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Introduce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroduceRepository extends JpaRepository<Introduce,String> {

    boolean existsByUid(String uid);
}
