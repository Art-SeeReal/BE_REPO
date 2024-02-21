package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.RequestDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDeleteRepository extends JpaRepository<RequestDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
