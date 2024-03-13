package com.ArtSeeReal.pro.repository.jpa.delete;

import com.ArtSeeReal.pro.entity.delete.RecruitmentDelete;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentDeleteRepository extends JpaRepository<RecruitmentDelete,String>, ModuleRepository {

}
