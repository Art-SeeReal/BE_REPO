package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentInfoDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentListDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentListResponseDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import com.ArtSeeReal.pro.entity.composite.FavoriteRecruitmentKey;
import com.ArtSeeReal.pro.entity.composite.UserLikeKey;
import com.ArtSeeReal.pro.entity.main.QRecruitment;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.repository.jpa.main.FavoriteRecruitmentsRepository;
import com.ArtSeeReal.pro.repository.jpa.main.UserLikesRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.RecruitmentQueryDslRepository;
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
public class RecruitmentQueryDslRepositoryImpl implements RecruitmentQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final UserLikesRepository userLikesRepository;
    private final FavoriteRecruitmentsRepository favoriteRecruitmentsRepository;

    private BooleanExpression addUserNicknameCondition(String nickname, BooleanExpression whereClause) {
        QUser user = QUser.user;
        return whereClause != null ?
                whereClause.and(user.nickname.toLowerCase().contains(nickname.toLowerCase())) :
                user.nickname.toLowerCase().contains(nickname.toLowerCase());
    }

    private BooleanExpression addPortfolioTitleCondition(String title, BooleanExpression whereClause) {
        QRecruitment recruitment = QRecruitment.recruitment;
        return whereClause != null ?
                whereClause.and(recruitment.title.toLowerCase().contains(title.toLowerCase())) :
                recruitment.title.toLowerCase().contains(title.toLowerCase());
    }

    private BooleanExpression addRegionTypeCondition(List<RegionType> regionTypes, BooleanExpression whereClause) {
        BooleanExpression regionTypeCondition = null;
        QRecruitment recruitment = QRecruitment.recruitment;
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

    private BooleanExpression addCategoryTypeCondition(List<CategoryType> categories, BooleanExpression whereClause) {
        BooleanExpression categoryTypeCondition = null;
        QRecruitment recruitment = QRecruitment.recruitment;
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

    @Override
    public RecruitmentListResponseDTO findListByRecruitmentDTO(RecruitmentListRequestDTO dto, String userUid) {
        QRecruitment recruitment = QRecruitment.recruitment;
        QUser user = QUser.user;

        BooleanExpression whereClause = buildWhereClause(dto);

        // Count total portfolios matching the criteria
        Long totalCount = jpaQueryFactory
                .select(recruitment.count())
                .from(recruitment)
                .join(user).on(recruitment.userUid.eq(user.uid))
                .where(whereClause)
                .fetchOne();

        // Fetch portfolios with additional information
        List<RecruitmentInfoDTO> recruitmentDTOList = jpaQueryFactory
                .select(Projections.constructor(RecruitmentInfoDTO.class,
                        recruitment.uid,
                        recruitment.thumbnail,
                        recruitment.title,
                        user.nickname,
                        recruitment.userUid,
                        recruitment.category,
                        recruitment.region,
                        Expressions.FALSE,
                        Expressions.FALSE,
                        user.uid,
                        user.userId))
                .from(recruitment)
                .join(user).on(recruitment.userUid.eq(user.uid))
                .where(whereClause)
                .orderBy(recruitment.regDate.desc())
                .limit(dto.getPostCount())
                .fetch();

        if(userUid == null){
            List<RecruitmentListDTO> result = recruitmentDTOList.stream()
                    .map(RecruitmentInfoDTO::infoToReadDTO)
                    .toList();
            return new RecruitmentListResponseDTO(result, Math.toIntExact(totalCount));
        } else {
            Set<String> favoriteRecruitmentSet = new HashSet<>(favoriteRecruitmentsRepository.findRecruitmentUidByUserUid(userUid));
            Set<String> userLikeSet = new HashSet<>(userLikesRepository.findYourUserUidByMyUserUid(userUid));

            List<RecruitmentListDTO> result = recruitmentDTOList.stream()
                    .map(infos -> infos.isSetting(favoriteRecruitmentSet,userLikeSet))
                    .toList();

            return new RecruitmentListResponseDTO(result, Math.toIntExact(totalCount));
        }
    }

    @Override
    public RecruitmentReadResponseDTO findRecruitmentReadByIdAndUserId(String recruitmentUid, String userUid) {
        QRecruitment recruitment = QRecruitment.recruitment;
        QUser user = QUser.user;

        RecruitmentReadDTO recruitmentReadDTO = jpaQueryFactory
                .select(Projections.constructor(RecruitmentReadDTO.class,
                        recruitment.uid,
                        recruitment.thumbnail,
                        recruitment.title,
                        user.nickname,
                        recruitment.region,
                        recruitment.category,
                        Expressions.FALSE,
                        Expressions.FALSE,
                        recruitment.userUid,
                        user.userId,
                        recruitment.viewCnt,
                        recruitment.regDate,
                        recruitment.dueDate,
                        recruitment.payment,
                        recruitment.content))
                .from(recruitment)
                .join(user).on(recruitment.userUid.eq(user.uid))
                .where(recruitment.uid.eq(recruitmentUid))
                .fetchOne();
        if(userUid == null)
            return new RecruitmentReadResponseDTO(Objects.requireNonNull(recruitmentReadDTO));
        else {
            boolean isUserLike = userLikesRepository
                    .existsById(new UserLikeKey(userUid, Objects.requireNonNull(recruitmentReadDTO).getWriterUid()));
            boolean isRecruitmentScrap = favoriteRecruitmentsRepository
                    .existsById(new FavoriteRecruitmentKey(userUid,recruitmentUid));
            return new RecruitmentReadResponseDTO(recruitmentReadDTO,isUserLike,isRecruitmentScrap);
        }
    }

    private BooleanExpression buildWhereClause(RecruitmentListRequestDTO dto) {
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

        // 카테고리 필터링 조건
        if (dto.getRegions() != null && !dto.getRegions().isEmpty())
            whereClause = addRegionTypeCondition(dto.getRegions(), whereClause);

        return whereClause;
    }
}
