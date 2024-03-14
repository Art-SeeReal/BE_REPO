package com.ArtSeeReal.pro.repository.jpa.delete;

import com.ArtSeeReal.pro.entity.delete.RequestCommentDelete;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCommentDeleteRepository extends JpaRepository<RequestCommentDelete,String>, ModuleRepository {

}
