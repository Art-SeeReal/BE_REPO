package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.UserDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeleteRepository extends JpaRepository<UserDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
