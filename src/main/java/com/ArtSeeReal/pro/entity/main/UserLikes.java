package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.composite.UserLikeKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity(name = "USER_LIKES")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLikes {
    @EmbeddedId
    private UserLikeKey pk;

    @Column(nullable = false)
    private LocalDateTime regDate;

    public UserLikes(UserLikeKey userLikes){
        this.pk = userLikes;
        this.regDate = LocalDateTime.now();
    }
}
