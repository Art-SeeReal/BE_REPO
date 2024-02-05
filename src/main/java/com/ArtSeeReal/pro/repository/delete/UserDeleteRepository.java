package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.UserDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeleteRepository extends JpaRepository<UserDelete,String> {

    boolean existsByUid(String uid);
}
