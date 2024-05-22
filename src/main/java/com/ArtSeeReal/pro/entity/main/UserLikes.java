package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.composite.UserLikeKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "LIKES_USER")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLikes {
    @EmbeddedId
    private UserLikeKey pk;
}
