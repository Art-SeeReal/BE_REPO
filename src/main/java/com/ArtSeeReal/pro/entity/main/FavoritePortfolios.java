package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.composite.FavoritePortfolioKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "FAVORITE_PORTFOLIO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePortfolios {
    @EmbeddedId
    private FavoritePortfolioKey pk;
}
