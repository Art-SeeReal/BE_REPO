package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLikes is a Querydsl query type for UserLikes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLikes extends EntityPathBase<UserLikes> {

    private static final long serialVersionUID = 84739251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLikes userLikes = new QUserLikes("userLikes");

    public final com.ArtSeeReal.pro.entity.composite.QUserLikeKey pk;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QUserLikes(String variable) {
        this(UserLikes.class, forVariable(variable), INITS);
    }

    public QUserLikes(Path<? extends UserLikes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLikes(PathMetadata metadata, PathInits inits) {
        this(UserLikes.class, metadata, inits);
    }

    public QUserLikes(Class<? extends UserLikes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new com.ArtSeeReal.pro.entity.composite.QUserLikeKey(forProperty("pk")) : null;
    }

}

