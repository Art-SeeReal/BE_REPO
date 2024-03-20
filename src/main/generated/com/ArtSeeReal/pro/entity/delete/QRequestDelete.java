package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestDelete is a Querydsl query type for RequestDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestDelete extends EntityPathBase<RequestDelete> {

    private static final long serialVersionUID = -1780152114L;

    public static final QRequestDelete requestDelete = new QRequestDelete("requestDelete");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath requestUid = createString("requestUid");

    public final BooleanPath secret = createBoolean("secret");

    public final StringPath title = createString("title");

    public final NumberPath<Long> type = createNumber("type", Long.class);

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public QRequestDelete(String variable) {
        super(RequestDelete.class, forVariable(variable));
    }

    public QRequestDelete(Path<? extends RequestDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestDelete(PathMetadata metadata) {
        super(RequestDelete.class, metadata);
    }

}

