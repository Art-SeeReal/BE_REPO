package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.composite.FavoritePortfolioKey;
import com.ArtSeeReal.pro.entity.main.FavoritePortfolios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePortfoliosRepository extends JpaRepository<FavoritePortfolios, FavoritePortfolioKey> {
}
