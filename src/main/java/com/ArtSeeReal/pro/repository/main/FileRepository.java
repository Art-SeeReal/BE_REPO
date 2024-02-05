package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,String> {

    boolean existsByUid(String uid);
}
