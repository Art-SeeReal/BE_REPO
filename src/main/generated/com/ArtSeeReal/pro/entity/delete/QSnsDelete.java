package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSnsDelete is a Querydsl query type for SnsDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSnsDelete extends EntityPathBase<SnsDelete> {

    private static final long serialVersionUID = 109460215L;

    public static final QSnsDelete snsDelete = new QSnsDelete("snsDelete");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    public final StringPath snsUid = createString("snsUid");

    public final StringPath uid = createString("uid");

    public QSnsDelete(String variable) {
        super(SnsDelete.class, forVariable(variable));
    }

    public QSnsDelete(Path<? extends SnsDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSnsDelete(PathMetadata metadata) {
        super(SnsDelete.class, metadata);
    }

}

