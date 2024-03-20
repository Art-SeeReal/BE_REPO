package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSns is a Querydsl query type for Sns
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSns extends EntityPathBase<Sns> {

    private static final long serialVersionUID = 1397480698L;

    public static final QSns sns = new QSns("sns");

    public final StringPath uid = createString("uid");

    public final StringPath url = createString("url");

    public final StringPath userUid = createString("userUid");

    public QSns(String variable) {
        super(Sns.class, forVariable(variable));
    }

    public QSns(Path<? extends Sns> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSns(PathMetadata metadata) {
        super(Sns.class, metadata);
    }

}

