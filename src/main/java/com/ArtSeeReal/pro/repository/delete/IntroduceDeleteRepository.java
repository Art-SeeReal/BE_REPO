package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.IntroduceDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroduceDeleteRepository extends JpaRepository<IntroduceDelete,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
