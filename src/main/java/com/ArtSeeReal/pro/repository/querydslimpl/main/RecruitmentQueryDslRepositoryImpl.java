package com.ArtSeeReal.pro.repository.querydslimpl.main;

import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import com.ArtSeeReal.pro.entity.main.QRecruitment;
import com.ArtSeeReal.pro.entity.main.QUser;
import com.ArtSeeReal.pro.repository.jpa.main.RecruitmentRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.RecruitmentQueryDslRepository;
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
public class RecruitmentQueryDslRepositoryImpl implements RecruitmentQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final RecruitmentRepository recruitmentRepository;

    @Override
    public Page<RecruitmentWithUserDTO> findByUserAndRecruitmentOrderByRegDateDesc(Pageable pageable) {
        QRecruitment recruitment = QRecruitment.recruitment;
        QUser user = QUser.user;

        List<RecruitmentWithUserDTO> content = jpaQueryFactory
                .select(Projections.constructor(RecruitmentWithUserDTO.class,
                        recruitment,
                        user))
                .from(recruitment)
                .join(user)
                .on(recruitment.userUid.eq(user.uid))
                .orderBy(recruitment.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = recruitmentRepository.count();

        return new PageImpl<>(content, pageable, total);
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
}
