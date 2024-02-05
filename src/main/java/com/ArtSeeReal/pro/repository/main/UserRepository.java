package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByUid(String uid);
}
