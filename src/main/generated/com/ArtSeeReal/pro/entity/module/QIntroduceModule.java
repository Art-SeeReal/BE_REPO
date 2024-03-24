package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIntroduceModule is a Querydsl query type for IntroduceModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QIntroduceModule extends EntityPathBase<IntroduceModule> {

    private static final long serialVersionUID = 1406072288L;

    public static final QIntroduceModule introduceModule = new QIntroduceModule("introduceModule");

    public final StringPath content = createString("content");

    public final StringPath userUid = createString("userUid");

    public QIntroduceModule(String variable) {
        super(IntroduceModule.class, forVariable(variable));
    }

    public QIntroduceModule(Path<? extends IntroduceModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIntroduceModule(PathMetadata metadata) {
        super(IntroduceModule.class, metadata);
    }

}

