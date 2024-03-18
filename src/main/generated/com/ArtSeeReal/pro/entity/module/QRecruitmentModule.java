package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitmentModule is a Querydsl query type for RecruitmentModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QRecruitmentModule extends EntityPathBase<RecruitmentModule> {

    private static final long serialVersionUID = -2069782691L;

    public static final QRecruitmentModule recruitmentModule = new QRecruitmentModule("recruitmentModule");

    public final NumberPath<Long> category = createNumber("category", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> dueDate = createDateTime("dueDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = createEnum("regionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final StringPath userUid = createString("userUid");

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

    public QRecruitmentModule(String variable) {
        super(RecruitmentModule.class, forVariable(variable));
    }

    public QRecruitmentModule(Path<? extends RecruitmentModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitmentModule(PathMetadata metadata) {
        super(RecruitmentModule.class, metadata);
    }

}

