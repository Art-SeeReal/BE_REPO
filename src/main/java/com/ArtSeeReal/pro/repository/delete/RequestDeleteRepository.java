package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.RequestDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDeleteRepository extends JpaRepository<RequestDelete,String> {

    boolean existsByUid(String uid);
}
