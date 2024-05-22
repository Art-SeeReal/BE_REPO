package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestModule is a Querydsl query type for RequestModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QRequestModule extends EntityPathBase<RequestModule> {

    private static final long serialVersionUID = -1398643568L;

    public static final QRequestModule requestModule = new QRequestModule("requestModule");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final BooleanPath secret = createBoolean("secret");

    public final StringPath title = createString("title");

    public final NumberPath<Long> type = createNumber("type", Long.class);

    public final StringPath userUid = createString("userUid");

    public QRequestModule(String variable) {
        super(RequestModule.class, forVariable(variable));
    }

    public QRequestModule(Path<? extends RequestModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestModule(PathMetadata metadata) {
        super(RequestModule.class, metadata);
    }

}

