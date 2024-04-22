package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.composite.UserLikeKey;
import com.ArtSeeReal.pro.entity.main.UserLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikesRepository extends JpaRepository<UserLikes, UserLikeKey> {
}
