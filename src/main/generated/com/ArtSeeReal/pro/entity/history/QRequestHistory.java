package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestHistory is a Querydsl query type for RequestHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestHistory extends EntityPathBase<RequestHistory> {

    private static final long serialVersionUID = -641881898L;

    public static final QRequestHistory requestHistory = new QRequestHistory("requestHistory");

    public final com.ArtSeeReal.pro.entity.module.QRequestModule _super = new com.ArtSeeReal.pro.entity.module.QRequestModule(this);

    //inherited
    public final StringPath content = _super.content;

    public final StringPath exContent = createString("exContent");

    public final BooleanPath exSecret = createBoolean("exSecret");

    public final StringPath exTitle = createString("exTitle");

    public final NumberPath<Long> exType = createNumber("exType", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

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

    public QRequestHistory(String variable) {
        super(RequestHistory.class, forVariable(variable));
    }

    public QRequestHistory(Path<? extends RequestHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestHistory(PathMetadata metadata) {
        super(RequestHistory.class, metadata);
    }

}

