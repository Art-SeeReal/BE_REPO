package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitmentDelete is a Querydsl query type for RecruitmentDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitmentDelete extends EntityPathBase<RecruitmentDelete> {

    private static final long serialVersionUID = 1517005851L;

    public static final QRecruitmentDelete recruitmentDelete = new QRecruitmentDelete("recruitmentDelete");

    public final com.ArtSeeReal.pro.entity.module.QRecruitmentModule _super = new com.ArtSeeReal.pro.entity.module.QRecruitmentModule(this);

    public final StringPath boardUid = createString("boardUid");

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> category = _super.category;

    //inherited
    public final StringPath content = _super.content;

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dueDate = _super.dueDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> regionType = _super.regionType;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    //inherited
    public final StringPath title = _super.title;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    //inherited
    public final NumberPath<Long> viewCnt = _super.viewCnt;

    public QRecruitmentDelete(String variable) {
        super(RecruitmentDelete.class, forVariable(variable));
    }

    public QRecruitmentDelete(Path<? extends RecruitmentDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitmentDelete(PathMetadata metadata) {
        super(RecruitmentDelete.class, metadata);
    }

}

