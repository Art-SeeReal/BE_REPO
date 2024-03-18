package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestCommentModule is a Querydsl query type for RequestCommentModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QRequestCommentModule extends EntityPathBase<RequestCommentModule> {

    private static final long serialVersionUID = 16633607L;

    public static final QRequestCommentModule requestCommentModule = new QRequestCommentModule("requestCommentModule");

    public final StringPath content = createString("content");

    public final StringPath postUid = createString("postUid");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath userUid = createString("userUid");

    public QRequestCommentModule(String variable) {
        super(RequestCommentModule.class, forVariable(variable));
    }

    public QRequestCommentModule(Path<? extends RequestCommentModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestCommentModule(PathMetadata metadata) {
        super(RequestCommentModule.class, metadata);
    }

}

