package com.ArtSeeReal.pro.repository.jpa.main;

import com.ArtSeeReal.pro.entity.composite.FavoritePortfolioKey;
import com.ArtSeeReal.pro.entity.main.FavoritePortfolios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritePortfoliosRepository extends JpaRepository<FavoritePortfolios, FavoritePortfolioKey> {
    @Query("SELECT ul.pk.portfolioUid FROM FAVORITE_PORTFOLIO ul WHERE ul.pk.userUid = :userUid")
    List<String> findPortfolioUidByUserUid(@Param("userUid") String userUid);
}
