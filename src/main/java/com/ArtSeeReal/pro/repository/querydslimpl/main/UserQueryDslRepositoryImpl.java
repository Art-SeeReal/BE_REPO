package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.response.user.ApplicantResponseDTO;
import com.ArtSeeReal.pro.dto.user.ApplicantDTO;
import com.ArtSeeReal.pro.dto.user.UserAuthorDTO;
import com.ArtSeeReal.pro.dto.user.UserPlannerDTO;
import com.ArtSeeReal.pro.dto.with.UserIntroduceDTO;
import com.ArtSeeReal.pro.dto.with.UserWithIntroduceDTO;
import com.ArtSeeReal.pro.entity.main.QApplyRecruitments;
import com.ArtSeeReal.pro.entity.main.QIntroduce;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.entity.main.QUserLikes;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.repository.jpa.main.ApplyRecruitmentsRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.UserQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class UserQueryDslRepositoryImpl implements UserQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final ApplyRecruitmentsRepository applyRecruitmentsRepository;
    @Override
    public UserWithIntroduceDTO findUserProfileByUserUid(String userUid) {
        QIntroduce introduce = QIntroduce.introduce;
        QUser user = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(UserWithIntroduceDTO.class, introduce, user))
                .from(introduce)
                .join(user)
                .on(introduce.uid.eq(user.uid))
                .fetchOne();
    }

    @Override
    public UserIntroduceDTO findUserIntroduceByUserUid(String userUid) {
        QIntroduce introduce = QIntroduce.introduce;
        QUser user = QUser.user;
        return jpaQueryFactory.select(
                        Projections.constructor(UserIntroduceDTO.class,
                                user.nickname,
                                user.emailSecret.when(true).then(user.email).otherwise(""),
                                user.phoneSecret.when(true).then(user.phone).otherwise(""),
                                introduce.content))
                .from(introduce)
                .join(user)
                .on(introduce.uid.eq(user.uid))
                .where(user.uid.eq(userUid))
                .fetchOne();
    }

    @Override
    public List<UserAuthorDTO> findUserLikeAuthorByUserUid(String userUid) {
        QUser user = QUser.user;
        QUserLikes userLikes = QUserLikes.userLikes;
        return jpaQueryFactory.select(
                        Projections.constructor(UserAuthorDTO.class,
                                user.userId,
                                user.userType,
                                user.nickname))
                .from(userLikes)
                .join(user)
                .on(userLikes.pk.yourUserUid.eq(user.uid))
                .where(userLikes.pk.myUserUid.eq(userUid))
                .where(user.userType.eq(UserType.AUTHOR))
                .orderBy(userLikes.regDate.desc())
                .fetch();
    }

    @Override
    public List<UserPlannerDTO> findUserLikePlannerByUserUid(String userUid) {
        QUser user = QUser.user;
        QUserLikes userLikes = QUserLikes.userLikes;
        return jpaQueryFactory.select(
                        Projections.constructor(UserPlannerDTO.class,
                                user.userId,
                                user.userType,
                                user.nickname))
                .from(userLikes)
                .join(user)
                .on(userLikes.pk.yourUserUid.eq(user.uid))
                .where(userLikes.pk.myUserUid.eq(userUid))
                .where(user.userType.eq(UserType.PLANNER))
                .orderBy(userLikes.regDate.desc())
                .fetch();
    }

    @Override
    public ApplicantResponseDTO findApplicantUsersByRecruitmentUid(String recruitmentUid) {
        QUser user = QUser.user;
        QApplyRecruitments applyRecruitments = QApplyRecruitments.applyRecruitments;

        List<ApplicantDTO> applicantDTOs = jpaQueryFactory.select(
                        Projections.constructor(ApplicantDTO.class,
                                user.name,
                                user.nickname,
                                user.phone,
                                user.email,
                                user.userId))
                .from(applyRecruitments)
                .join(user).on(applyRecruitments.pk.userUid.eq(user.uid))
                .where(applyRecruitments.pk.recruitmentUid.eq(recruitmentUid))
                .orderBy(applyRecruitments.regDate.desc())
                .fetch();

        Long totalcount = jpaQueryFactory
                .select(applyRecruitments.count())
                .from(applyRecruitments)
                .where(applyRecruitments.pk.recruitmentUid.eq(recruitmentUid))
                .fetchOne();

        return new ApplicantResponseDTO(applicantDTOs, Math.toIntExact(totalcount));
    }
}
