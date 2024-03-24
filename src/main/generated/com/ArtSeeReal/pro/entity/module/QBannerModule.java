package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBannerModule is a Querydsl query type for BannerModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBannerModule extends EntityPathBase<BannerModule> {

    private static final long serialVersionUID = 2075758435L;

    public static final QBannerModule bannerModule = new QBannerModule("bannerModule");

    public final StringPath linkUrl = createString("linkUrl");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath url = createString("url");

    public QBannerModule(String variable) {
        super(BannerModule.class, forVariable(variable));
    }

    public QBannerModule(Path<? extends BannerModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBannerModule(PathMetadata metadata) {
        super(BannerModule.class, metadata);
    }

}

