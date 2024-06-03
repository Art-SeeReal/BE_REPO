package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.composite.UserLikeKey;
import com.ArtSeeReal.pro.entity.main.UserLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLikesRepository extends JpaRepository<UserLikes, UserLikeKey> {
    @Query("SELECT ul.pk.yourUserUid FROM USER_LIKES ul WHERE ul.pk.myUserUid = :myUserUid")
    List<String> findYourUserUidByMyUserUid(@Param("myUserUid") String myUserUid);
}
