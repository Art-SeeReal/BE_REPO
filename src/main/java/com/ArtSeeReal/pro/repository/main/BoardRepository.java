package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,String> {

    boolean existsByUid(String uid);
}
