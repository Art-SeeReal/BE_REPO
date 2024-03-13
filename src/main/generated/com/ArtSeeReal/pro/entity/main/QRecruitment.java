package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitment is a Querydsl query type for Recruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitment extends EntityPathBase<Recruitment> {

    private static final long serialVersionUID = 738544414L;

    public static final QRecruitment recruitment = new QRecruitment("recruitment");

    public final NumberPath<Long> category = createNumber("category", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> dueDate = createDateTime("dueDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = createEnum("regionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

    public QRecruitment(String variable) {
        super(Recruitment.class, forVariable(variable));
    }

    public QRecruitment(Path<? extends Recruitment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitment(PathMetadata metadata) {
        super(Recruitment.class, metadata);
    }

}

