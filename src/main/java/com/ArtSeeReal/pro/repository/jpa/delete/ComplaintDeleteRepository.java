package com.ArtSeeReal.pro.repository.jpa.delete;

import com.ArtSeeReal.pro.entity.delete.ComplaintDelete;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintDeleteRepository extends JpaRepository<ComplaintDelete,String>, ModuleRepository {

}
