package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserDelete is a Querydsl query type for UserDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDelete extends EntityPathBase<UserDelete> {

    private static final long serialVersionUID = 847825442L;

    public static final QUserDelete userDelete = new QUserDelete("userDelete");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    public final StringPath email = createString("email");

    public final BooleanPath emailSecret = createBoolean("emailSecret");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final BooleanPath phoneSecret = createBoolean("phoneSecret");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = createEnum("regionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath uid = createString("uid");

    public final StringPath userId = createString("userId");

    public final EnumPath<com.ArtSeeReal.pro.enums.UserType> userType = createEnum("userType", com.ArtSeeReal.pro.enums.UserType.class);

    public final StringPath userUid = createString("userUid");

    public QUserDelete(String variable) {
        super(UserDelete.class, forVariable(variable));
    }

    public QUserDelete(Path<? extends UserDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserDelete(PathMetadata metadata) {
        super(UserDelete.class, metadata);
    }

}

