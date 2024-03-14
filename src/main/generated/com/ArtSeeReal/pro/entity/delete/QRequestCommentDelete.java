package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestCommentDelete is a Querydsl query type for RequestCommentDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestCommentDelete extends EntityPathBase<RequestCommentDelete> {

    private static final long serialVersionUID = -1917313945L;

    public static final QRequestCommentDelete requestCommentDelete = new QRequestCommentDelete("requestCommentDelete");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    public final StringPath postUid = createString("postUid");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath requestCommentUid = createString("requestCommentUid");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public QRequestCommentDelete(String variable) {
        super(RequestCommentDelete.class, forVariable(variable));
    }

    public QRequestCommentDelete(Path<? extends RequestCommentDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestCommentDelete(PathMetadata metadata) {
        super(RequestCommentDelete.class, metadata);
    }

}

