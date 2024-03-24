package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserObjDelete is a Querydsl query type for UserObjDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserObjDelete extends EntityPathBase<UserObjDelete> {

    private static final long serialVersionUID = 1086102955L;

    public static final QUserObjDelete userObjDelete = new QUserObjDelete("userObjDelete");

    public final com.ArtSeeReal.pro.entity.module.QUserObjModule _super = new com.ArtSeeReal.pro.entity.module.QUserObjModule(this);

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.ObjType> targetType = _super.targetType;

    //inherited
    public final StringPath targetUid = _super.targetUid;

    public final StringPath uid = createString("uid");

    public final StringPath userObjUid = createString("userObjUid");

    //inherited
    public final StringPath userUid = _super.userUid;

    public QUserObjDelete(String variable) {
        super(UserObjDelete.class, forVariable(variable));
    }

    public QUserObjDelete(Path<? extends UserObjDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserObjDelete(PathMetadata metadata) {
        super(UserObjDelete.class, metadata);
    }

}

