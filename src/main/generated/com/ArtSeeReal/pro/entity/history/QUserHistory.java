package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHistory is a Querydsl query type for UserHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserHistory extends EntityPathBase<UserHistory> {

    private static final long serialVersionUID = -575426024L;

    public static final QUserHistory userHistory = new QUserHistory("userHistory");

    public final com.ArtSeeReal.pro.entity.module.QUserModule _super = new com.ArtSeeReal.pro.entity.module.QUserModule(this);

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final BooleanPath emailSecret = _super.emailSecret;

    public final StringPath exEmail = createString("exEmail");

    public final BooleanPath exEmailSecret = createBoolean("exEmailSecret");

    public final StringPath exName = createString("exName");

    public final StringPath exNickname = createString("exNickname");

    public final StringPath exPassword = createString("exPassword");

    public final StringPath exPhone = createString("exPhone");

    public final BooleanPath exPhoneSecret = createBoolean("exPhoneSecret");

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> exRegionType = createEnum("exRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.UserType> exUserType = createEnum("exUserType", com.ArtSeeReal.pro.enums.UserType.class);

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

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

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = _super.regionType;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userId = _super.userId;

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.UserType> userType = _super.userType;

    public final StringPath userUid = createString("userUid");

    public QUserHistory(String variable) {
        super(UserHistory.class, forVariable(variable));
    }

    public QUserHistory(Path<? extends UserHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHistory(PathMetadata metadata) {
        super(UserHistory.class, metadata);
    }

}

