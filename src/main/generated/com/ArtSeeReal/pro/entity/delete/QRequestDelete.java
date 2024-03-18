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

    public final com.ArtSeeReal.pro.entity.module.QRequestModule _super = new com.ArtSeeReal.pro.entity.module.QRequestModule(this);

    //inherited
    public final StringPath content = _super.content;

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath requestUid = createString("requestUid");

    //inherited
    public final BooleanPath secret = _super.secret;

    //inherited
    public final StringPath title = _super.title;

    //inherited
    public final NumberPath<Long> type = _super.type;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

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

