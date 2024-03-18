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

    public final com.ArtSeeReal.pro.entity.module.QPortfolioModule _super = new com.ArtSeeReal.pro.entity.module.QPortfolioModule(this);

    //inherited
    public final NumberPath<Long> category = _super.category;

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = _super.regionType;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    //inherited
    public final StringPath title = _super.title;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    //inherited
    public final NumberPath<Long> viewCnt = _super.viewCnt;

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

