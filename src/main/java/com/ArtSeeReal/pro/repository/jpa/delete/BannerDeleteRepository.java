package com.ArtSeeReal.pro.repository.jpa.delete;

import com.ArtSeeReal.pro.entity.delete.BannerDelete;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerDeleteRepository extends JpaRepository<BannerDelete,String>, ModuleRepository {

}
