package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPortfolio is a Querydsl query type for Portfolio
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPortfolio extends EntityPathBase<Portfolio> {

    private static final long serialVersionUID = 884865866L;

    public static final QPortfolio portfolio = new QPortfolio("portfolio");

    public final NumberPath<Long> category = createNumber("category", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = createEnum("regionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

    public QPortfolio(String variable) {
        super(Portfolio.class, forVariable(variable));
    }

    public QPortfolio(Path<? extends Portfolio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPortfolio(PathMetadata metadata) {
        super(Portfolio.class, metadata);
    }

}

