package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPortfolioHistory is a Querydsl query type for PortfolioHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPortfolioHistory extends EntityPathBase<PortfolioHistory> {

    private static final long serialVersionUID = -1133210691L;

    public static final QPortfolioHistory portfolioHistory = new QPortfolioHistory("portfolioHistory");

    public final com.ArtSeeReal.pro.entity.module.QPortfolioModule _super = new com.ArtSeeReal.pro.entity.module.QPortfolioModule(this);

    public final StringPath boardUid = createString("boardUid");

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> category = _super.category;

    //inherited
    public final StringPath content = _super.content;

    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> exCategory = createEnum("exCategory", com.ArtSeeReal.pro.enums.CategoryType.class);

    public final StringPath exContent = createString("exContent");

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> exRegionType = createEnum("exRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath exThumbnail = createString("exThumbnail");

    public final StringPath exTitle = createString("exTitle");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> region = _super.region;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    //inherited
    public final StringPath title = _super.title;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    //inherited
    public final NumberPath<Long> viewCnt = _super.viewCnt;

    public QPortfolioHistory(String variable) {
        super(PortfolioHistory.class, forVariable(variable));
    }

    public QPortfolioHistory(Path<? extends PortfolioHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPortfolioHistory(PathMetadata metadata) {
        super(PortfolioHistory.class, metadata);
    }

}

