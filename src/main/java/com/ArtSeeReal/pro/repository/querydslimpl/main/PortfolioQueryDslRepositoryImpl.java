package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import com.ArtSeeReal.pro.entity.main.QPortfolio;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.repository.jpa.main.PortfolioRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.PortfolioQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PortfolioQueryDslRepositoryImpl implements PortfolioQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final PortfolioRepository portfolioRepository;

    @Override
    public Page<PortfolioWithUserDTO> findByUserAndPortfolioOrderByRegDateDesc(Pageable pageable) {
        QPortfolio portfolio = QPortfolio.portfolio;
        QUser user = QUser.user;

        List<PortfolioWithUserDTO> content = jpaQueryFactory
                .select(Projections.constructor(PortfolioWithUserDTO.class,
                        portfolio,
                        user))
                .from(portfolio)
                .join(user)
                .on(portfolio.userUid.eq(user.uid))
                .orderBy(portfolio.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
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
