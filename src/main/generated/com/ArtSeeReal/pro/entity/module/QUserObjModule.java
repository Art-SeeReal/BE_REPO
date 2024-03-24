package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserObjModule is a Querydsl query type for UserObjModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUserObjModule extends EntityPathBase<UserObjModule> {

    private static final long serialVersionUID = 1467611501L;

    public static final QUserObjModule userObjModule = new QUserObjModule("userObjModule");

    public final EnumPath<com.ArtSeeReal.pro.enums.ObjType> targetType = createEnum("targetType", com.ArtSeeReal.pro.enums.ObjType.class);

    public final StringPath targetUid = createString("targetUid");

    public final StringPath userUid = createString("userUid");

    public QUserObjModule(String variable) {
        super(UserObjModule.class, forVariable(variable));
    }

    public QUserObjModule(Path<? extends UserObjModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserObjModule(PathMetadata metadata) {
        super(UserObjModule.class, metadata);
    }

}

