package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import com.ArtSeeReal.pro.entity.main.QPortfolio;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.repository.querydsl.main.PortfolioQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PortfolioQueryDslRepositoryImpl implements PortfolioQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PortfolioWithUserDTO> findByUserAndPortfolioOrderByRegDateDesc(PortfolioReadRequestDTO dto) {
        QPortfolio portfolio = QPortfolio.portfolio;
        QUser user = QUser.user;

        BooleanExpression whereClause = null;

        if (!dto.getNickname().isEmpty()) {
            whereClause = user.nickname.toLowerCase().contains(dto.getNickname().toLowerCase());
        }

        if (!dto.getTitle().isEmpty()) {
            BooleanExpression titleCondition = portfolio.title.toLowerCase().contains(dto.getTitle().toLowerCase());
            whereClause = whereClause != null ? whereClause.or(titleCondition) : titleCondition;
        }

        if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
            for (Object categoryObj : dto.getCategories()) {
                if (categoryObj instanceof CategoryType) {
                    CategoryType category = (CategoryType) categoryObj;
                    BooleanExpression categoryCondition = portfolio.category.eq(category);
                    whereClause = whereClause != null ? whereClause.or(categoryCondition) : categoryCondition;
                }
            }
        }

        if (dto.getRegionTypes() != null && !dto.getRegionTypes().isEmpty()) {
            for (Object regionObj : dto.getRegionTypes()) {
                if (regionObj instanceof RegionType) {
                    RegionType region = (RegionType) regionObj;
                    BooleanExpression regionTypeCondition = portfolio.region.eq(region);
                    whereClause = whereClause != null ? whereClause.or(regionTypeCondition) : regionTypeCondition;
                }
            }
        }

        int offset = dto.getPageNum() != null ? dto.getPageNum() * (dto.getLimit() != null ? dto.getLimit() : 10) : 0;
        int limit = dto.getLimit() != null ? dto.getLimit() : 10;

        return jpaQueryFactory
                .select(Projections.constructor(PortfolioWithUserDTO.class, portfolio, user))
                .from(portfolio)
                .join(user).on(portfolio.userUid.eq(user.uid))
                .where(whereClause)
                .orderBy(dto.getSortType().equalsIgnoreCase("desc") ? portfolio.regDate.desc() : portfolio.regDate.asc().nullsLast())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public PortfolioWithUserDTO findUserAndPortfolioByUid(String uid) {
        QPortfolio portfolio = QPortfolio.portfolio;
        QUser user = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(PortfolioWithUserDTO.class,
                        portfolio,
                        user))
                .from(portfolio)
                .join(user)
                .on(portfolio.userUid.eq(user.uid))
                .where(portfolio.uid.eq(uid))
                .fetchOne();
    }
}
