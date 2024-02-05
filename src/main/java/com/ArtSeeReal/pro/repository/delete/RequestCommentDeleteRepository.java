package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.RequestCommentDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCommentDeleteRepository extends JpaRepository<RequestCommentDelete,String> {

    boolean existsByUid(String uid);
}
