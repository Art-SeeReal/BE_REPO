package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.File;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
