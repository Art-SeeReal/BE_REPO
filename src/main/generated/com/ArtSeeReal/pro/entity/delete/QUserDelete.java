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

    public final com.ArtSeeReal.pro.entity.module.QUserModule _super = new com.ArtSeeReal.pro.entity.module.QUserModule(this);

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final BooleanPath emailSecret = _super.emailSecret;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath nickname = _super.nickname;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final StringPath phone = _super.phone;

    //inherited
    public final BooleanPath phoneSecret = _super.phoneSecret;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userId = _super.userId;

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.UserType> userType = _super.userType;

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

