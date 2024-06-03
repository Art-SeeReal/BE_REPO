package com.ArtSeeReal.pro.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoriteRecruitmentKey is a Querydsl query type for FavoriteRecruitmentKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFavoriteRecruitmentKey extends BeanPath<FavoriteRecruitmentKey> {

    private static final long serialVersionUID = 1918692253L;

    public static final QFavoriteRecruitmentKey favoriteRecruitmentKey = new QFavoriteRecruitmentKey("favoriteRecruitmentKey");

    public final StringPath recruitmentUid = createString("recruitmentUid");

    public final StringPath userUid = createString("userUid");

    public QFavoriteRecruitmentKey(String variable) {
        super(FavoriteRecruitmentKey.class, forVariable(variable));
    }

    public QFavoriteRecruitmentKey(Path<? extends FavoriteRecruitmentKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoriteRecruitmentKey(PathMetadata metadata) {
        super(FavoriteRecruitmentKey.class, metadata);
    }

}

