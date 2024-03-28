package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerformanceCommentModule is a Querydsl query type for PerformanceCommentModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QPerformanceCommentModule extends EntityPathBase<PerformanceCommentModule> {

    private static final long serialVersionUID = -46870138L;

    public static final QPerformanceCommentModule performanceCommentModule = new QPerformanceCommentModule("performanceCommentModule");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath targetUid = createString("targetUid");

    public final StringPath UserUid = createString("UserUid");

    public QPerformanceCommentModule(String variable) {
        super(PerformanceCommentModule.class, forVariable(variable));
    }

    public QPerformanceCommentModule(Path<? extends PerformanceCommentModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerformanceCommentModule(PathMetadata metadata) {
        super(PerformanceCommentModule.class, metadata);
    }

}

