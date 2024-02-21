package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>, ModuleRepository {

    boolean existsByUid(String uid);
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
