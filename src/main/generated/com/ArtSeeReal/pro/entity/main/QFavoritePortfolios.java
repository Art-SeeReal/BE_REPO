package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavoritePortfolios is a Querydsl query type for FavoritePortfolios
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoritePortfolios extends EntityPathBase<FavoritePortfolios> {

    private static final long serialVersionUID = -1441553627L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFavoritePortfolios favoritePortfolios = new QFavoritePortfolios("favoritePortfolios");

    public final com.ArtSeeReal.pro.entity.composite.QFavoritePortfolioKey pk;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QFavoritePortfolios(String variable) {
        this(FavoritePortfolios.class, forVariable(variable), INITS);
    }

    public QFavoritePortfolios(Path<? extends FavoritePortfolios> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFavoritePortfolios(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFavoritePortfolios(PathMetadata metadata, PathInits inits) {
        this(FavoritePortfolios.class, metadata, inits);
    }

    public QFavoritePortfolios(Class<? extends FavoritePortfolios> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new com.ArtSeeReal.pro.entity.composite.QFavoritePortfolioKey(forProperty("pk")) : null;
    }

}

