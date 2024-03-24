package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBannerHistory is a Querydsl query type for BannerHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBannerHistory extends EntityPathBase<BannerHistory> {

    private static final long serialVersionUID = -374339625L;

    public static final QBannerHistory bannerHistory = new QBannerHistory("bannerHistory");

    public final com.ArtSeeReal.pro.entity.module.QBannerModule _super = new com.ArtSeeReal.pro.entity.module.QBannerModule(this);

    public final StringPath bannerUid = createString("bannerUid");

    public final StringPath exLinkUrl = createString("exLinkUrl");

    public final StringPath exUrl = createString("exUrl");

    //inherited
    public final StringPath linkUrl = _super.linkUrl;

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath url = _super.url;

    public QBannerHistory(String variable) {
        super(BannerHistory.class, forVariable(variable));
    }

    public QBannerHistory(Path<? extends BannerHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBannerHistory(PathMetadata metadata) {
        super(BannerHistory.class, metadata);
    }

}

