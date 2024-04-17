package com.ArtSeeReal.pro.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLikeKey is a Querydsl query type for UserLikeKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserLikeKey extends BeanPath<UserLikeKey> {

    private static final long serialVersionUID = 1551455231L;

    public static final QUserLikeKey userLikeKey = new QUserLikeKey("userLikeKey");

    public final StringPath myUserUid = createString("myUserUid");

    public final StringPath yourUserUid = createString("yourUserUid");

    public QUserLikeKey(String variable) {
        super(UserLikeKey.class, forVariable(variable));
    }

    public QUserLikeKey(Path<? extends UserLikeKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLikeKey(PathMetadata metadata) {
        super(UserLikeKey.class, metadata);
    }

}

