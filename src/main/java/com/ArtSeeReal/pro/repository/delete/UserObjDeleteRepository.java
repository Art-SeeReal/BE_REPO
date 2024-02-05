package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.UserObjDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserObjDeleteRepository extends JpaRepository<UserObjDelete,String> {

    boolean existsByUid(String uid);
}
