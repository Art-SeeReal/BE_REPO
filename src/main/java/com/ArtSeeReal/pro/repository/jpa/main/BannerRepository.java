package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.Banner;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, String>, ModuleRepository {

}