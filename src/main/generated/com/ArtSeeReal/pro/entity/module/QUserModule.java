package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserModule is a Querydsl query type for UserModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUserModule extends EntityPathBase<UserModule> {

    private static final long serialVersionUID = -1023248510L;

    public static final QUserModule userModule = new QUserModule("userModule");

    public final StringPath email = createString("email");

    public final BooleanPath emailSecret = createBoolean("emailSecret");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final BooleanPath phoneSecret = createBoolean("phoneSecret");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public final EnumPath<com.ArtSeeReal.pro.enums.UserType> userType = createEnum("userType", com.ArtSeeReal.pro.enums.UserType.class);

    public QUserModule(String variable) {
        super(UserModule.class, forVariable(variable));
    }

    public QUserModule(Path<? extends UserModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserModule(PathMetadata metadata) {
        super(UserModule.class, metadata);
    }

}

