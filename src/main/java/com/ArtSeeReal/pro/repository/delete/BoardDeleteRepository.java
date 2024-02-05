package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.BannerDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDeleteRepository extends JpaRepository<BannerDelete,String> {

    boolean existsByUid(String uid);
}
