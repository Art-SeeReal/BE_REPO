package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner,String> {

    boolean existsByUid(String uid);
}
