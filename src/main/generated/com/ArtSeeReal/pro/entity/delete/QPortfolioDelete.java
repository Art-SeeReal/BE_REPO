package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPortfolioDelete is a Querydsl query type for PortfolioDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPortfolioDelete extends EntityPathBase<PortfolioDelete> {

    private static final long serialVersionUID = -1469427769L;

    public static final QPortfolioDelete portfolioDelete = new QPortfolioDelete("portfolioDelete");

    public final com.ArtSeeReal.pro.entity.module.QPortfolioModule _super = new com.ArtSeeReal.pro.entity.module.QPortfolioModule(this);

    public final StringPath boardUid = createString("boardUid");

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> category = _super.category;

    //inherited
    public final StringPath content = _super.content;

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    //inherited
    public final StringPath title = _super.title;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    //inherited
    public final NumberPath<Long> viewCnt = _super.viewCnt;

    public QPortfolioDelete(String variable) {
        super(PortfolioDelete.class, forVariable(variable));
    }

    public QPortfolioDelete(Path<? extends PortfolioDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPortfolioDelete(PathMetadata metadata) {
        super(PortfolioDelete.class, metadata);
    }

}

