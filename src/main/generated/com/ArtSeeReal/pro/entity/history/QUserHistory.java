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

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final StringPath newEmail = createString("newEmail");

    public final BooleanPath newEmailSecret = createBoolean("newEmailSecret");

    public final StringPath newName = createString("newName");

    public final StringPath newNickname = createString("newNickname");

    public final StringPath newPassword = createString("newPassword");

    public final StringPath newPhone = createString("newPhone");

    public final BooleanPath newPhoneSecret = createBoolean("newPhoneSecret");

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> newRegionType = createEnum("newRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.UserType> newUserType = createEnum("newUserType", com.ArtSeeReal.pro.enums.UserType.class);

    public final StringPath oldEmail = createString("oldEmail");

    public final BooleanPath oldEmailSecret = createBoolean("oldEmailSecret");

    public final StringPath oldName = createString("oldName");

    public final StringPath oldNickname = createString("oldNickname");

    public final StringPath oldPassword = createString("oldPassword");

    public final StringPath oldPhone = createString("oldPhone");

    public final BooleanPath oldPhoneSecret = createBoolean("oldPhoneSecret");

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> oldRegionType = createEnum("oldRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.UserType> oldUserType = createEnum("oldUserType", com.ArtSeeReal.pro.enums.UserType.class);

    public final StringPath uid = createString("uid");

    public final StringPath userId = createString("userId");

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

