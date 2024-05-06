package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.main.RefreshEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<RefreshEntity, String> {

    Boolean existsByRefresh(String refresh); // todo pk로 조회해라 (UserRepository 의 findByUserId 이용)
    @Transactional
    void deleteByRefresh(String refresh); // todo pk로 삭제해라
}