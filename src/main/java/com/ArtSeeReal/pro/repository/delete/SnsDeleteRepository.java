package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.SnsDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsDeleteRepository extends JpaRepository<SnsDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
