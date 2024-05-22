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

    public final com.ArtSeeReal.pro.entity.module.QSnsModule _super = new com.ArtSeeReal.pro.entity.module.QSnsModule(this);

    public final StringPath exUrl = createString("exUrl");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath snsUid = createString("snsUid");

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath url = _super.url;

    //inherited
    public final StringPath userUid = _super.userUid;

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

