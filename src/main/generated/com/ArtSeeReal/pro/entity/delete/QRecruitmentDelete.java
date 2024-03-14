package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitmentDelete is a Querydsl query type for RecruitmentDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitmentDelete extends EntityPathBase<RecruitmentDelete> {

    private static final long serialVersionUID = 1517005851L;

    public static final QRecruitmentDelete recruitmentDelete = new QRecruitmentDelete("recruitmentDelete");

    public final StringPath boardUid = createString("boardUid");

    public final NumberPath<Long> category = createNumber("category", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    public final DateTimePath<java.time.LocalDateTime> dueDate = createDateTime("dueDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = createEnum("regionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

    public QRecruitmentDelete(String variable) {
        super(RecruitmentDelete.class, forVariable(variable));
    }

    public QRecruitmentDelete(Path<? extends RecruitmentDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitmentDelete(PathMetadata metadata) {
        super(RecruitmentDelete.class, metadata);
    }

}

