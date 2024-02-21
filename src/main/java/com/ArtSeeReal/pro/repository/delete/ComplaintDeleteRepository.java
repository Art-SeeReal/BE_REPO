package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.ComplaintDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintDeleteRepository extends JpaRepository<ComplaintDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
