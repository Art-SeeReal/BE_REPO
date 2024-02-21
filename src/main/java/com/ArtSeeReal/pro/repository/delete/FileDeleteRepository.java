package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.FileDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDeleteRepository extends JpaRepository<FileDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
