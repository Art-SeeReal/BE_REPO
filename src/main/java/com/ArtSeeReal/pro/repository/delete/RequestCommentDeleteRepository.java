package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.RequestCommentDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCommentDeleteRepository extends JpaRepository<RequestCommentDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
