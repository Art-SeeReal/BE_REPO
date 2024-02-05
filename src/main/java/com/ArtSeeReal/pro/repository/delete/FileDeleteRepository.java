package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.FileDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDeleteRepository extends JpaRepository<FileDelete,String> {

    boolean existsByUid(String uid);
}
