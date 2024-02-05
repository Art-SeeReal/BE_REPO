package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.RequestComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCommentRepository extends JpaRepository<RequestComment,String> {

    boolean existsByUid(String uid);
}
