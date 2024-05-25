package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.user.UserAuthorDTO;
import com.ArtSeeReal.pro.dto.user.UserPlannerDTO;
import com.ArtSeeReal.pro.dto.with.UserIntroduceDTO;
import com.ArtSeeReal.pro.dto.with.UserWithIntroduceDTO;
import com.ArtSeeReal.pro.entity.main.QIntroduce;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.entity.main.QUserLikes;
import com.ArtSeeReal.pro.enums.UserType;
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
}
