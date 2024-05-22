package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavoriteRecruitments is a Querydsl query type for FavoriteRecruitments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoriteRecruitments extends EntityPathBase<FavoriteRecruitments> {

    private static final long serialVersionUID = 536612561L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFavoriteRecruitments favoriteRecruitments = new QFavoriteRecruitments("favoriteRecruitments");

    public final com.ArtSeeReal.pro.entity.composite.QFavoriteRecruitmentKey pk;

    public QFavoriteRecruitments(String variable) {
        this(FavoriteRecruitments.class, forVariable(variable), INITS);
    }

    public QFavoriteRecruitments(Path<? extends FavoriteRecruitments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFavoriteRecruitments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFavoriteRecruitments(PathMetadata metadata, PathInits inits) {
        this(FavoriteRecruitments.class, metadata, inits);
    }

    public QFavoriteRecruitments(Class<? extends FavoriteRecruitments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new com.ArtSeeReal.pro.entity.composite.QFavoriteRecruitmentKey(forProperty("pk")) : null;
    }

}

