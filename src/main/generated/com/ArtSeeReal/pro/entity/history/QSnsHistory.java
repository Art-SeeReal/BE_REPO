package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSnsHistory is a Querydsl query type for SnsHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSnsHistory extends EntityPathBase<SnsHistory> {

    private static final long serialVersionUID = 399443533L;

    public static final QSnsHistory snsHistory = new QSnsHistory("snsHistory");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final StringPath newUrl = createString("newUrl");

    public final StringPath oldUrl = createString("oldUrl");

    public final StringPath snsUid = createString("snsUid");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public QSnsHistory(String variable) {
        super(SnsHistory.class, forVariable(variable));
    }

    public QSnsHistory(Path<? extends SnsHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSnsHistory(PathMetadata metadata) {
        super(SnsHistory.class, metadata);
    }

}

