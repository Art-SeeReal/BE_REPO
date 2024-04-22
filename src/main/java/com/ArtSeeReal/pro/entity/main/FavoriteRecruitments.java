package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.composite.FavoriteRecruitmentKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "FAVORITE_RECRUITMENT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRecruitments {
    @EmbeddedId
    private FavoriteRecruitmentKey pk;
}
