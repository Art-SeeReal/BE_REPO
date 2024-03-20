package com.ArtSeeReal.pro.repository.jpa.delete;

import com.ArtSeeReal.pro.entity.delete.PortfolioDelete;
import com.ArtSeeReal.pro.repository.jpa.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioDeleteRepository extends JpaRepository<PortfolioDelete,String>, ModuleRepository {

}
