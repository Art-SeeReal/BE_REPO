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

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final StringPath newContent = createString("newContent");

    public final BooleanPath newSecret = createBoolean("newSecret");

    public final StringPath newTitle = createString("newTitle");

    public final NumberPath<Long> newType = createNumber("newType", Long.class);

    public final StringPath oldContent = createString("oldContent");

    public final BooleanPath oldSecret = createBoolean("oldSecret");

    public final StringPath oldTitle = createString("oldTitle");

    public final NumberPath<Long> oldType = createNumber("oldType", Long.class);

    public final StringPath requestUid = createString("requestUid");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

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

