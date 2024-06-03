package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.composite.FavoriteRecruitmentKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "FAVORITE_RECRUITMENT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRecruitments {
    @EmbeddedId
    private FavoriteRecruitmentKey pk;
    @Column(nullable = false)
    private LocalDateTime regDate;

    public FavoriteRecruitments(FavoriteRecruitmentKey favoriteRecruitmentKey){
        this.pk = favoriteRecruitmentKey;
        this.regDate = LocalDateTime.now();
    }
}
