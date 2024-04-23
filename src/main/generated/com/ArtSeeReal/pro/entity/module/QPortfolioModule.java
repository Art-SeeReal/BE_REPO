package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPortfolioModule is a Querydsl query type for PortfolioModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QPortfolioModule extends EntityPathBase<PortfolioModule> {

    private static final long serialVersionUID = 1778708297L;

    public static final QPortfolioModule portfolioModule = new QPortfolioModule("portfolioModule");

    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> category = createEnum("category", com.ArtSeeReal.pro.enums.CategoryType.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final StringPath userUid = createString("userUid");

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

    public QPortfolioModule(String variable) {
        super(PortfolioModule.class, forVariable(variable));
    }

    public QPortfolioModule(Path<? extends PortfolioModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPortfolioModule(PathMetadata metadata) {
        super(PortfolioModule.class, metadata);
    }

}

