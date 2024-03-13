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

    public final StringPath boardUid = createString("boardUid");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final NumberPath<Long> newCategory = createNumber("newCategory", Long.class);

    public final StringPath newContent = createString("newContent");

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> newRegionType = createEnum("newRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath newThumbnail = createString("newThumbnail");

    public final StringPath newTitle = createString("newTitle");

    public final NumberPath<Long> oldCategory = createNumber("oldCategory", Long.class);

    public final StringPath oldContent = createString("oldContent");

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> oldRegionType = createEnum("oldRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath oldThumbnail = createString("oldThumbnail");

    public final StringPath oldTitle = createString("oldTitle");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

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

