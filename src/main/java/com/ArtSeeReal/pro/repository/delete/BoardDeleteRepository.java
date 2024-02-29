package com.ArtSeeReal.pro.repository.delete;

import com.ArtSeeReal.pro.entity.delete.BoardDelete;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDeleteRepository extends JpaRepository<BoardDelete,String>, ModuleRepository {

}
