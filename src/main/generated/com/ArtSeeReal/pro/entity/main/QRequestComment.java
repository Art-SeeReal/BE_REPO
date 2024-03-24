package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestComment is a Querydsl query type for RequestComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestComment extends EntityPathBase<RequestComment> {

    private static final long serialVersionUID = -810164210L;

    public static final QRequestComment requestComment = new QRequestComment("requestComment");

    public final com.ArtSeeReal.pro.entity.module.QRequestCommentModule _super = new com.ArtSeeReal.pro.entity.module.QRequestCommentModule(this);

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final StringPath postUid = _super.postUid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    public QRequestComment(String variable) {
        super(RequestComment.class, forVariable(variable));
    }

    public QRequestComment(Path<? extends RequestComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestComment(PathMetadata metadata) {
        super(RequestComment.class, metadata);
    }

}

