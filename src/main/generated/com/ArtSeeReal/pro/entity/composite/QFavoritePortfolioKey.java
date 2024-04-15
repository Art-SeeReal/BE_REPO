package com.ArtSeeReal.pro.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoritePortfolioKey is a Querydsl query type for FavoritePortfolioKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFavoritePortfolioKey extends BeanPath<FavoritePortfolioKey> {

    private static final long serialVersionUID = -899392783L;

    public static final QFavoritePortfolioKey favoritePortfolioKey = new QFavoritePortfolioKey("favoritePortfolioKey");

    public final StringPath portfolioUid = createString("portfolioUid");

    public final StringPath userUid = createString("userUid");

    public QFavoritePortfolioKey(String variable) {
        super(FavoritePortfolioKey.class, forVariable(variable));
    }

    public QFavoritePortfolioKey(Path<? extends FavoritePortfolioKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoritePortfolioKey(PathMetadata metadata) {
        super(FavoritePortfolioKey.class, metadata);
    }

}

