package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBanner is a Querydsl query type for Banner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBanner extends EntityPathBase<Banner> {

    private static final long serialVersionUID = 730732650L;

    public static final QBanner banner = new QBanner("banner");

    public final com.ArtSeeReal.pro.entity.module.QBannerModule _super = new com.ArtSeeReal.pro.entity.module.QBannerModule(this);

    //inherited
    public final StringPath imageUrl = _super.imageUrl;

    //inherited
    public final StringPath linkUrl = _super.linkUrl;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uid = createString("uid");

    public QBanner(String variable) {
        super(Banner.class, forVariable(variable));
    }

    public QBanner(Path<? extends Banner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBanner(PathMetadata metadata) {
        super(Banner.class, metadata);
    }

}

