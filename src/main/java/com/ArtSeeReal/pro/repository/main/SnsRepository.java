package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Sns;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsRepository extends JpaRepository<Sns,String>, ModuleRepository {

}
