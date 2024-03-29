package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerformanceComment is a Querydsl query type for PerformanceComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformanceComment extends EntityPathBase<PerformanceComment> {

    private static final long serialVersionUID = 1965591629L;

    public static final QPerformanceComment performanceComment = new QPerformanceComment("performanceComment");

    public final com.ArtSeeReal.pro.entity.module.QPerformanceCommentModule _super = new com.ArtSeeReal.pro.entity.module.QPerformanceCommentModule(this);

    //inherited
    public final StringPath comment = _super.comment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final StringPath targetUid = _super.targetUid;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath UserUid = _super.UserUid;

    public QPerformanceComment(String variable) {
        super(PerformanceComment.class, forVariable(variable));
    }

    public QPerformanceComment(Path<? extends PerformanceComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerformanceComment(PathMetadata metadata) {
        super(PerformanceComment.class, metadata);
    }

}

