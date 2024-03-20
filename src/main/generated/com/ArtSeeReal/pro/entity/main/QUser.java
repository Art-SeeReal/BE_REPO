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

