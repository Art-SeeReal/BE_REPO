package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.SnsDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsDeleteRepository extends JpaRepository<SnsDelete,String> {

    boolean existsByUid(String uid);
}
