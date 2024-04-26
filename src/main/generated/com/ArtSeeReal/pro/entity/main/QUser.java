package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 372292745L;

    public static final QUser user = new QUser("user");

    public final com.ArtSeeReal.pro.entity.module.QUserModule _super = new com.ArtSeeReal.pro.entity.module.QUserModule(this);

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

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

