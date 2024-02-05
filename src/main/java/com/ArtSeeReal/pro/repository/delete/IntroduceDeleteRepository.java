package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.IntroduceDelete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroduceDeleteRepository extends JpaRepository<IntroduceDelete,String> {

    boolean existsByUid(String uid);
}
