package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.RequestComment;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCommentRepository extends JpaRepository<RequestComment,String>, ModuleRepository {

}
