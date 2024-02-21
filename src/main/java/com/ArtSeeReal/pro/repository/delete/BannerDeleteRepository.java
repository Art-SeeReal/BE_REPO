package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.BannerDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerDeleteRepository extends JpaRepository<BannerDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
