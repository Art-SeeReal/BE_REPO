package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import com.ArtSeeReal.pro.entity.main.QPortfolio;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.repository.jpa.main.PortfolioRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.PortfolioQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PortfolioQueryDslRepositoryImpl implements PortfolioQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final PortfolioRepository portfolioRepository;

    @Override
    public Page<PortfolioWithUserDTO> findByUserAndPortfolioOrderByRegDateDesc(PortfolioReadRequestDTO dto) {
        QPortfolio portfolio = QPortfolio.portfolio;
        QUser user = QUser.user;

        BooleanExpression whereClause = null;

        if (!StringUtils.isEmpty(dto.getNickname())) {
            whereClause = user.nickname.eq(dto.getNickname());
        }

        if (!StringUtils.isEmpty(dto.getTitle())) {
            BooleanExpression titleCondition = portfolio.title.contains(dto.getTitle());
            whereClause = whereClause != null ? whereClause.or(titleCondition) : titleCondition;
        }

        if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
            BooleanExpression categoryCondition = portfolio.category.in(dto.getCategories());
            whereClause = whereClause != null ? whereClause.or(categoryCondition) : categoryCondition;
        }

        if (dto.getRegionTypes() != null && !dto.getRegionTypes().isEmpty()) {
            BooleanExpression regionTypeCondition = portfolio.regionType.in(dto.getRegionTypes());
            whereClause = whereClause != null ? whereClause.or(regionTypeCondition) : regionTypeCondition;
        }

        // 쿼리에 조건 추가
        List<PortfolioWithUserDTO> content = jpaQueryFactory
                .select(Projections.constructor(PortfolioWithUserDTO.class, portfolio, user))
                .from(portfolio)
                .join(user).on(portfolio.userUid.eq(user.uid))
                .where(whereClause)
                .orderBy(dto.getSortType().equalsIgnoreCase("desc") ? portfolio.regDate.desc() : portfolio.regDate.asc())
                .offset(dto.getPageNum() == null ? 0 : dto.getPageNum() * (dto.getLimit() == null ? 10 : dto.getLimit()))
                .limit(dto.getLimit() == null ? 10 : dto.getLimit())
                .fetch();
        long total = portfolioRepository.count();

        return new PageImpl<>(content, pageable, total);
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
