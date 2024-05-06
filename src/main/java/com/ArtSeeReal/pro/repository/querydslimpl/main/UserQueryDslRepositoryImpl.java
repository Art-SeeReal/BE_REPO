package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import com.ArtSeeReal.pro.dto.with.UserWithIntroduceDTO;
import com.ArtSeeReal.pro.entity.main.QIntroduce;
import com.ArtSeeReal.pro.entity.main.QRecruitment;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.repository.querydsl.main.UserQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

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
}
