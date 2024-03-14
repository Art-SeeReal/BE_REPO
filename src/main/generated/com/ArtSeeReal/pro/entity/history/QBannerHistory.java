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

    public final StringPath bannerUid = createString("bannerUid");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final StringPath newLinkUrl = createString("newLinkUrl");

    public final StringPath newUrl = createString("newUrl");

    public final StringPath oldLinkUrl = createString("oldLinkUrl");

    public final StringPath oldUrl = createString("oldUrl");

    public final StringPath uid = createString("uid");

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

