package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Board;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,String>, ModuleRepository {

    boolean existsByUid(String uid);
}
