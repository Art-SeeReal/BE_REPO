package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.UserObjDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserObjDeleteRepository extends JpaRepository<UserObjDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
