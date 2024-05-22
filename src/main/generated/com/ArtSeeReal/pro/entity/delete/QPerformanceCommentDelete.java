package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerformanceCommentDelete is a Querydsl query type for PerformanceCommentDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformanceCommentDelete extends EntityPathBase<PerformanceCommentDelete> {

    private static final long serialVersionUID = 844703334L;

    public static final QPerformanceCommentDelete performanceCommentDelete = new QPerformanceCommentDelete("performanceCommentDelete");

    public final com.ArtSeeReal.pro.entity.module.QPerformanceCommentModule _super = new com.ArtSeeReal.pro.entity.module.QPerformanceCommentModule(this);

    //inherited
    public final StringPath comment = _super.comment;

    public final StringPath commentUid = createString("commentUid");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final StringPath targetUid = _super.targetUid;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath UserUid = _super.UserUid;

    public QPerformanceCommentDelete(String variable) {
        super(PerformanceCommentDelete.class, forVariable(variable));
    }

    public QPerformanceCommentDelete(Path<? extends PerformanceCommentDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerformanceCommentDelete(PathMetadata metadata) {
        super(PerformanceCommentDelete.class, metadata);
    }

}

