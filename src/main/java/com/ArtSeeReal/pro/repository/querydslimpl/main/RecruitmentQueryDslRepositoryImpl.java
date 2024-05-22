package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import com.ArtSeeReal.pro.entity.main.QRecruitment;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.repository.querydsl.main.RecruitmentQueryDslRepository;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class RecruitmentQueryDslRepositoryImpl implements RecruitmentQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RecruitmentWithUserDTO> findByUserAndRecruitmentOrderByRegDateDesc(RecruitmentReadRequestDTO dto) {
        QRecruitment recruitment = QRecruitment.recruitment;
        QUser user = QUser.user;

        BooleanExpression whereClause = buildWhereClause(dto, user, recruitment);

        int offset = calculateOffset(dto);
        int limit = dto.getLimit() != null ? dto.getLimit() : 10;

        return jpaQueryFactory
                .select(Projections.constructor(RecruitmentWithUserDTO.class, recruitment, user))
                .from(recruitment)
                .join(user)
                .on(recruitment.userUid.eq(user.uid))
                .where(whereClause)
                .orderBy(getOrderSpecifier(dto))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    private BooleanExpression buildWhereClause(RecruitmentReadRequestDTO dto, QUser user, QRecruitment recruitment) {
        BooleanExpression whereClause = null;

        // 닉네임 필터링 조건
        if (dto.getNickname() != null && !dto.getNickname().isEmpty())
            whereClause = addUserNicknameCondition(dto.getNickname(), user, whereClause);

        // 제목 필터링 조건
        if (dto.getTitle() != null && !dto.getTitle().isEmpty())
            whereClause = addPortfolioTitleCondition(dto.getTitle(), recruitment, whereClause);

        // 지역 필터링 조건
        if (dto.getRegionTypes() != null && !dto.getRegionTypes().isEmpty())
            whereClause = addRegionTypeCondition(dto.getRegionTypes(), recruitment, whereClause);

        // 카테고리 필터링 조건
        if (dto.getCategories() != null && !dto.getCategories().isEmpty())
            whereClause = addCategoryTypeCondition(dto.getCategories(), recruitment, whereClause);

        return whereClause;
    }

    private BooleanExpression addUserNicknameCondition(String nickname, QUser user, BooleanExpression whereClause) {
        return whereClause != null ?
                whereClause.and(user.nickname.toLowerCase().contains(nickname.toLowerCase())) :
                user.nickname.toLowerCase().contains(nickname.toLowerCase());
    }

    private BooleanExpression addPortfolioTitleCondition(String title, QRecruitment recruitment, BooleanExpression whereClause) {
        return whereClause != null ?
                whereClause.and(recruitment.title.toLowerCase().contains(title.toLowerCase())) :
                recruitment.title.toLowerCase().contains(title.toLowerCase());
    }

    private BooleanExpression addRegionTypeCondition(List<RegionType> regionTypes, QRecruitment recruitment, BooleanExpression whereClause) {
        BooleanExpression regionTypeCondition = null;
        for (Object regionObj : regionTypes) {
            if (regionObj instanceof RegionType) {
                RegionType region = (RegionType) regionObj;
                BooleanExpression condition = recruitment.region.eq(region);
                regionTypeCondition = regionTypeCondition != null ? regionTypeCondition.or(condition) : condition;
            }
        }
        return whereClause != null ?
                whereClause.and(regionTypeCondition) :
                regionTypeCondition;
    }

    private BooleanExpression addCategoryTypeCondition(List<CategoryType> categories, QRecruitment recruitment, BooleanExpression whereClause) {
        BooleanExpression categoryTypeCondition = null;
        for (Object categoryObj : categories) {
            if (categoryObj instanceof CategoryType) {
                CategoryType category = (CategoryType) categoryObj;
                BooleanExpression condition = recruitment.category.eq(category);
                categoryTypeCondition = categoryTypeCondition != null ? categoryTypeCondition.or(condition) : condition;
            }
        }
        return whereClause != null ?
                whereClause.and(categoryTypeCondition) :
                categoryTypeCondition;
    }

    private int calculateOffset(RecruitmentReadRequestDTO dto) {
        return dto.getPageNum() != null ?
                dto.getPageNum() * (dto.getLimit() != null ? dto.getLimit() : 10) :
                0;
    }

    @Override
    public RecruitmentWithUserDTO findUserAndRecruitmentByUid(String uid) {
        QRecruitment recruitment = QRecruitment.recruitment;
        QUser user = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(RecruitmentWithUserDTO.class,
                        recruitment,
                        user))
                .from(recruitment)
                .join(user)
                .on(recruitment.userUid.eq(user.uid))
                .where(recruitment.uid.eq(uid))
                .fetchOne();
    }

    private OrderSpecifier<?> getOrderSpecifier(RecruitmentReadRequestDTO dto) {
        Order type = dto.getSortType().equals("ASC")? Order.ASC : Order.DESC;

        switch (dto.getSortField()) {
            case "regDate":
                return new OrderSpecifier<>(type, QRecruitment.recruitment.regDate);
            case "title":
                return new OrderSpecifier<>(type, QRecruitment.recruitment.title);
            default:
                return new OrderSpecifier<>(type, Expressions.stringPath(dto.getSortField()));
        }
    }
}
