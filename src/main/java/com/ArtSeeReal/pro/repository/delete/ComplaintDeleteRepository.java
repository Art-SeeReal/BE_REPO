package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.ComplaintDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintDeleteRepository extends JpaRepository<ComplaintDelete,String> {

    boolean existsByUid(String uid);
}
