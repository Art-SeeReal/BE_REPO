package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSnsModule is a Querydsl query type for SnsModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QSnsModule extends EntityPathBase<SnsModule> {

    private static final long serialVersionUID = 584269625L;

    public static final QSnsModule snsModule = new QSnsModule("snsModule");

    public final StringPath url = createString("url");

    public final StringPath userUid = createString("userUid");

    public QSnsModule(String variable) {
        super(SnsModule.class, forVariable(variable));
    }

    public QSnsModule(Path<? extends SnsModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSnsModule(PathMetadata metadata) {
        super(SnsModule.class, metadata);
    }

}

