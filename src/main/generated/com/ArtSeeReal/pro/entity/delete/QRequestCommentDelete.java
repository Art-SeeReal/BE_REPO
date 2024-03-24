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

    public final com.ArtSeeReal.pro.entity.module.QRequestCommentModule _super = new com.ArtSeeReal.pro.entity.module.QRequestCommentModule(this);

    //inherited
    public final StringPath content = _super.content;

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    //inherited
    public final StringPath postUid = _super.postUid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath requestCommentUid = createString("requestCommentUid");

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

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

