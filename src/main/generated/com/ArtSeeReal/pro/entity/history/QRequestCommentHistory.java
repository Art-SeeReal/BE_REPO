package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestCommentHistory is a Querydsl query type for RequestCommentHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestCommentHistory extends EntityPathBase<RequestCommentHistory> {

    private static final long serialVersionUID = 1741013683L;

    public static final QRequestCommentHistory requestCommentHistory = new QRequestCommentHistory("requestCommentHistory");

    public final com.ArtSeeReal.pro.entity.module.QRequestCommentModule _super = new com.ArtSeeReal.pro.entity.module.QRequestCommentModule(this);

    //inherited
    public final StringPath content = _super.content;

    public final StringPath exContent = createString("exContent");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    //inherited
    public final StringPath postUid = _super.postUid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath requestCommentUid = createString("requestCommentUid");

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    public QRequestCommentHistory(String variable) {
        super(RequestCommentHistory.class, forVariable(variable));
    }

    public QRequestCommentHistory(Path<? extends RequestCommentHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestCommentHistory(PathMetadata metadata) {
        super(RequestCommentHistory.class, metadata);
    }

}

