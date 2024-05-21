package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.Introduce;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroduceRepository extends JpaRepository<Introduce,String>, ModuleRepository {
}
