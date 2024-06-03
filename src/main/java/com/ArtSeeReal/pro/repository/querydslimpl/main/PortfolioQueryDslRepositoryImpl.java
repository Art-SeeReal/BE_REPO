package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioInfoDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioListDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadDTO;
import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioListRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioListResponseDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import com.ArtSeeReal.pro.entity.composite.FavoritePortfolioKey;
import com.ArtSeeReal.pro.entity.composite.UserLikeKey;
import com.ArtSeeReal.pro.entity.main.QFavoritePortfolios;
import com.ArtSeeReal.pro.entity.main.QPortfolio;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.repository.jpa.main.FavoritePortfoliosRepository;
import com.ArtSeeReal.pro.repository.jpa.main.UserLikesRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.PortfolioQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PortfolioQueryDslRepositoryImpl implements PortfolioQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final UserLikesRepository userLikesRepository;
    private final FavoritePortfoliosRepository favoritePortfoliosRepository;

    private BooleanExpression addUserNicknameCondition(String nickname, BooleanExpression whereClause) {
        QUser user = QUser.user;
        return whereClause != null ?
                whereClause.and(user.nickname.toLowerCase().contains(nickname.toLowerCase())) :
                user.nickname.toLowerCase().contains(nickname.toLowerCase());
    }

    private BooleanExpression addPortfolioTitleCondition(String title, BooleanExpression whereClause) {
        QPortfolio portfolio = QPortfolio.portfolio;
        return whereClause != null ?
                whereClause.and(portfolio.title.toLowerCase().contains(title.toLowerCase())) :
                portfolio.title.toLowerCase().contains(title.toLowerCase());
    }

    private BooleanExpression addCategoryTypeCondition(List<CategoryType> categories, BooleanExpression whereClause) {
        BooleanExpression categoryTypeCondition = null;
        QPortfolio portfolio = QPortfolio.portfolio;
        for (CategoryType categoryObj : categories) {
            if (categoryObj != null) {
                BooleanExpression condition = portfolio.category.eq(categoryObj);
                categoryTypeCondition = categoryTypeCondition != null ? categoryTypeCondition.or(condition) : condition;
            }
        }
        return whereClause != null ?
                whereClause.and(categoryTypeCondition) :
                categoryTypeCondition;
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

    @Override
    public PortfolioListResponseDTO findListByPortfolioDTO(PortfolioListRequestDTO dto, String userUid) {
        QPortfolio portfolio = QPortfolio.portfolio;
        QUser user = QUser.user;

        BooleanExpression whereClause = buildWhereClause(dto);

        // Count total portfolios matching the criteria
        Long totalCount = jpaQueryFactory
                .select(portfolio.count())
                .from(portfolio)
                .join(user).on(portfolio.userUid.eq(user.uid))
                .where(whereClause)
                .fetchOne();

        // Fetch portfolios with additional information
        List<PortfolioInfoDTO> portfolioInfoDTOList = jpaQueryFactory
                .select(Projections.constructor(PortfolioInfoDTO.class,
                        portfolio.uid,
                        portfolio.thumbnail,
                        portfolio.title,
                        user.nickname,
                        portfolio.userUid,
                        portfolio.category,
                        Expressions.FALSE,
                        Expressions.FALSE,
                        user.uid,
                        user.userId))
                .from(portfolio)
                .join(user).on(portfolio.userUid.eq(user.uid))
                .where(whereClause)
                .orderBy(portfolio.regDate.desc())
                .limit(dto.getPostCount())
                .fetch();
        if(userUid == null){
            List<PortfolioListDTO> result = portfolioInfoDTOList.stream()
                    .map(PortfolioInfoDTO::infoToReadDTO)
                    .toList();
            return new PortfolioListResponseDTO(result, Math.toIntExact(totalCount));
        } else {
            Set<String> favoritePortfolioSet = new HashSet<>(favoritePortfoliosRepository.findPortfolioUidByUserUid(userUid));
            Set<String> userLikeSet = new HashSet<>(userLikesRepository.findYourUserUidByMyUserUid(userUid));

            List<PortfolioListDTO> result = portfolioInfoDTOList.stream()
                    .map(infos -> infos.isSetting(favoritePortfolioSet,userLikeSet))
                    .toList();

            return new PortfolioListResponseDTO(result, Math.toIntExact(totalCount));
        }
    }

    @Override
    public PortfolioReadResponseDTO findPortfolioReadByIdAndUserId(String portfolioUid, String userUid) {
        QPortfolio portfolio = QPortfolio.portfolio;
        QUser user = QUser.user;

        PortfolioReadDTO portfolioReadDTO = jpaQueryFactory
                .select(Projections.constructor(PortfolioReadDTO.class,
                        portfolio.uid,
                        portfolio.thumbnail,
                        portfolio.title,
                        user.nickname,
                        portfolio.category,
                        Expressions.FALSE,
                        Expressions.FALSE,
                        portfolio.userUid,
                        user.userId,
                        portfolio.viewCnt,
                        portfolio.regDate,
                        portfolio.content))
                .from(portfolio)
                .join(user).on(portfolio.userUid.eq(user.uid))
                .where(portfolio.uid.eq(portfolioUid))
                .fetchOne();
        if(userUid == null)
            return new PortfolioReadResponseDTO(Objects.requireNonNull(portfolioReadDTO));
        else {
            boolean isUserLike = userLikesRepository
                    .existsById(new UserLikeKey(userUid, Objects.requireNonNull(portfolioReadDTO).getWriterUid()));
            boolean isPortfolioScrap = favoritePortfoliosRepository
                    .existsById(new FavoritePortfolioKey(userUid,portfolioUid));
            return new PortfolioReadResponseDTO(portfolioReadDTO,isUserLike,isPortfolioScrap);
        }
    }

    @Override
    public PortfolioListResponseDTO findMyScrapPortfolioByUserUid(String userUid, Long postCount) {
        QPortfolio portfolio = QPortfolio.portfolio;
        QFavoritePortfolios favoritePortfolios = QFavoritePortfolios.favoritePortfolios;
        QUser user = QUser.user;

        List<PortfolioInfoDTO> portfolioInfoDTOList = jpaQueryFactory
                .select(Projections.constructor(PortfolioInfoDTO.class,
                        portfolio.uid,
                        portfolio.thumbnail,
                        portfolio.title,
                        user.nickname,
                        portfolio.userUid,
                        portfolio.category,
                        Expressions.TRUE,
                        Expressions.FALSE,
                        user.uid,
                        user.userId))
                .from(favoritePortfolios)
                .join(portfolio).on(favoritePortfolios.pk.portfolioUid.eq(portfolio.uid))
                .join(user).on(portfolio.userUid.eq(user.uid))
                .where(favoritePortfolios.pk.userUid.eq(userUid))
                .orderBy(portfolio.regDate.desc())
                .limit(postCount)
                .fetch();
        Long count = jpaQueryFactory
                .select(favoritePortfolios.count())
                .where(favoritePortfolios.pk.userUid.eq(userUid))
                .fetchOne();

        Set<String> userLikeSet = new HashSet<>(userLikesRepository.findYourUserUidByMyUserUid(userUid));
        List<PortfolioListDTO> result = portfolioInfoDTOList.stream()
                .map(infos -> infos.userLikeSetting(userLikeSet))
                .toList();

        return new PortfolioListResponseDTO(result, Math.toIntExact(count));
    }

    private BooleanExpression buildWhereClause(PortfolioListRequestDTO dto) {
        BooleanExpression whereClause = null;

        // 닉네임 필터링 조건
        if (dto.getKeyWords() != null && !dto.getKeyWords().isEmpty())
            whereClause = addUserNicknameCondition(dto.getKeyWords(), whereClause);

        // 제목 필터링 조건
        if (dto.getKeyWords() != null && !dto.getKeyWords().isEmpty())
            whereClause = addPortfolioTitleCondition(dto.getKeyWords(), whereClause);

        // 카테고리 필터링 조건
        if (dto.getFields() != null && !dto.getFields().isEmpty())
            whereClause = addCategoryTypeCondition(dto.getFields(), whereClause);

        return whereClause;
    }
}
