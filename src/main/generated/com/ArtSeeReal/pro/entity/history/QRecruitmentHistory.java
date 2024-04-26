package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitmentHistory is a Querydsl query type for RecruitmentHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitmentHistory extends EntityPathBase<RecruitmentHistory> {

    private static final long serialVersionUID = 396911785L;

    public static final QRecruitmentHistory recruitmentHistory = new QRecruitmentHistory("recruitmentHistory");

    public final com.ArtSeeReal.pro.entity.module.QRecruitmentModule _super = new com.ArtSeeReal.pro.entity.module.QRecruitmentModule(this);

    public final StringPath boardUid = createString("boardUid");

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> category = _super.category;

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dueDate = _super.dueDate;

    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> exCategory = createEnum("exCategory", com.ArtSeeReal.pro.enums.CategoryType.class);

    public final StringPath exContent = createString("exContent");

    public final DateTimePath<java.time.LocalDateTime> exDueDate = createDateTime("exDueDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> exPayment = createNumber("exPayment", Long.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> exRegionType = createEnum("exRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath exThumbnail = createString("exThumbnail");

    public final StringPath exTitle = createString("exTitle");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    //inherited
    public final NumberPath<Long> payment = _super.payment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> region = _super.region;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    //inherited
    public final StringPath title = _super.title;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    //inherited
    public final NumberPath<Long> viewCnt = _super.viewCnt;

    public QRecruitmentHistory(String variable) {
        super(RecruitmentHistory.class, forVariable(variable));
    }

    public QRecruitmentHistory(Path<? extends RecruitmentHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitmentHistory(PathMetadata metadata) {
        super(RecruitmentHistory.class, metadata);
    }

}

