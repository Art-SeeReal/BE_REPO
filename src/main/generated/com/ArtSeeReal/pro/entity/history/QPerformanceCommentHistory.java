package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerformanceCommentHistory is a Querydsl query type for PerformanceCommentHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformanceCommentHistory extends EntityPathBase<PerformanceCommentHistory> {

    private static final long serialVersionUID = -1900948716L;

    public static final QPerformanceCommentHistory performanceCommentHistory = new QPerformanceCommentHistory("performanceCommentHistory");

    public final com.ArtSeeReal.pro.entity.module.QPerformanceCommentModule _super = new com.ArtSeeReal.pro.entity.module.QPerformanceCommentModule(this);

    //inherited
    public final StringPath comment = _super.comment;

    public final StringPath commentUid = createString("commentUid");

    public final StringPath exComment = createString("exComment");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final StringPath targetUid = _super.targetUid;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath UserUid = _super.UserUid;

    public QPerformanceCommentHistory(String variable) {
        super(PerformanceCommentHistory.class, forVariable(variable));
    }

    public QPerformanceCommentHistory(Path<? extends PerformanceCommentHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerformanceCommentHistory(PathMetadata metadata) {
        super(PerformanceCommentHistory.class, metadata);
    }

}

