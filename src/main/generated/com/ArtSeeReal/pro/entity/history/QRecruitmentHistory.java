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

    public final StringPath boardUid = createString("boardUid");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final NumberPath<Long> newCategory = createNumber("newCategory", Long.class);

    public final StringPath newContent = createString("newContent");

    public final DateTimePath<java.time.LocalDateTime> newDueDate = createDateTime("newDueDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> newRegionType = createEnum("newRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath newThumbnail = createString("newThumbnail");

    public final StringPath newTitle = createString("newTitle");

    public final NumberPath<Long> oldCategory = createNumber("oldCategory", Long.class);

    public final StringPath oldContent = createString("oldContent");

    public final DateTimePath<java.time.LocalDateTime> oldDueDate = createDateTime("oldDueDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ArtSeeReal.pro.enums.RegionType> oldRegionType = createEnum("oldRegionType", com.ArtSeeReal.pro.enums.RegionType.class);

    public final StringPath oldThumbnail = createString("oldThumbnail");

    public final StringPath oldTitle = createString("oldTitle");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

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

