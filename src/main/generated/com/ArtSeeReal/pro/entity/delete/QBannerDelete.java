package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBannerDelete is a Querydsl query type for BannerDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBannerDelete extends EntityPathBase<BannerDelete> {

    private static final long serialVersionUID = -1104114237L;

    public static final QBannerDelete bannerDelete = new QBannerDelete("bannerDelete");

    public final com.ArtSeeReal.pro.entity.module.QBannerModule _super = new com.ArtSeeReal.pro.entity.module.QBannerModule(this);

    public final StringPath bannerUid = createString("bannerUid");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    //inherited
    public final StringPath imageUrl = _super.imageUrl;

    //inherited
    public final StringPath linkUrl = _super.linkUrl;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uid = createString("uid");

    public QBannerDelete(String variable) {
        super(BannerDelete.class, forVariable(variable));
    }

    public QBannerDelete(Path<? extends BannerDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBannerDelete(PathMetadata metadata) {
        super(BannerDelete.class, metadata);
    }

}

