package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import com.ArtSeeReal.pro.entity.main.QPortfolio;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.repository.querydsl.main.PortfolioQueryDslRepository;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
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

        BooleanExpression whereClause = buildWhereClause(dto, user, portfolio);

        int offset = calculateOffset(dto);
        int limit = dto.getLimit() != null ? dto.getLimit() : 10;

        return jpaQueryFactory
                .select(Projections.constructor(PortfolioWithUserDTO.class, portfolio, user))
                .from(portfolio)
                .join(user)
                .on(portfolio.userUid.eq(user.uid))
                .where(whereClause)
                .orderBy(getOrderSpecifier(dto))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    private BooleanExpression buildWhereClause(PortfolioReadRequestDTO dto, QUser user, QPortfolio portfolio) {
        BooleanExpression whereClause = null;

        // 닉네임 필터링 조건
        if (dto.getNickname() != null && !dto.getNickname().isEmpty())
            whereClause = addUserNicknameCondition(dto.getNickname(), user, whereClause);

        // 제목 필터링 조건
        if (dto.getTitle() != null && !dto.getTitle().isEmpty())
            whereClause = addPortfolioTitleCondition(dto.getTitle(), portfolio, whereClause);

        // 지역 필터링 조건
        if (dto.getRegionTypes() != null && !dto.getRegionTypes().isEmpty())
            whereClause = addRegionTypeCondition(dto.getRegionTypes(), portfolio, whereClause);

        // 카테고리 필터링 조건
        if (dto.getCategories() != null && !dto.getCategories().isEmpty())
            whereClause = addCategoryTypeCondition(dto.getCategories(), portfolio, whereClause);

        return whereClause;
    }

    private BooleanExpression addUserNicknameCondition(String nickname, QUser user, BooleanExpression whereClause) {
        return whereClause != null ?
                whereClause.and(user.nickname.toLowerCase().contains(nickname.toLowerCase())) :
                user.nickname.toLowerCase().contains(nickname.toLowerCase());
    }

    private BooleanExpression addPortfolioTitleCondition(String title, QPortfolio portfolio, BooleanExpression whereClause) {
        return whereClause != null ?
                whereClause.and(portfolio.title.toLowerCase().contains(title.toLowerCase())) :
                portfolio.title.toLowerCase().contains(title.toLowerCase());
    }

    private BooleanExpression addRegionTypeCondition(List<RegionType> regionTypes, QPortfolio portfolio, BooleanExpression whereClause) {
        BooleanExpression regionTypeCondition = null;
        for (Object regionObj : regionTypes) {
            if (regionObj instanceof RegionType) {
                RegionType region = (RegionType) regionObj;
                BooleanExpression condition = portfolio.region.eq(region);
                regionTypeCondition = regionTypeCondition != null ? regionTypeCondition.or(condition) : condition;
            }
        }
        return whereClause != null ?
                whereClause.and(regionTypeCondition) :
                regionTypeCondition;
    }

    private BooleanExpression addCategoryTypeCondition(List<CategoryType> categories, QPortfolio portfolio, BooleanExpression whereClause) {
        BooleanExpression categoryTypeCondition = null;
        for (Object categoryObj : categories) {
            if (categoryObj instanceof CategoryType) {
                CategoryType category = (CategoryType) categoryObj;
                BooleanExpression condition = portfolio.category.eq(category);
                categoryTypeCondition = categoryTypeCondition != null ? categoryTypeCondition.or(condition) : condition;
            }
        }
        return whereClause != null ?
                whereClause.and(categoryTypeCondition) :
                categoryTypeCondition;
    }

    private int calculateOffset(PortfolioReadRequestDTO dto) {
        return dto.getPageNum() != null ?
                dto.getPageNum() * (dto.getLimit() != null ? dto.getLimit() : 10) :
                0;
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

    private OrderSpecifier<?> getOrderSpecifier(PortfolioReadRequestDTO dto) {
        Order type = dto.getSortType().equals("ASC")? Order.ASC : Order.DESC;

        switch (dto.getSortField()) {
            case "regDate":
                return new OrderSpecifier<>(type, QPortfolio.portfolio.regDate);
            case "title":
                return new OrderSpecifier<>(type, QPortfolio.portfolio.title);
            default:
                return new OrderSpecifier<>(type, Expressions.stringPath(dto.getSortField()));
        }
    }
}
