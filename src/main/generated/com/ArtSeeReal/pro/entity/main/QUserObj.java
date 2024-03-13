package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserObj is a Querydsl query type for UserObj
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserObj extends EntityPathBase<UserObj> {

    private static final long serialVersionUID = 1367687086L;

    public static final QUserObj userObj = new QUserObj("userObj");

    public final EnumPath<com.ArtSeeReal.pro.enums.ObjType> targetType = createEnum("targetType", com.ArtSeeReal.pro.enums.ObjType.class);

    public final StringPath targetUid = createString("targetUid");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public QUserObj(String variable) {
        super(UserObj.class, forVariable(variable));
    }

    public QUserObj(Path<? extends UserObj> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserObj(PathMetadata metadata) {
        super(UserObj.class, metadata);
    }

}

