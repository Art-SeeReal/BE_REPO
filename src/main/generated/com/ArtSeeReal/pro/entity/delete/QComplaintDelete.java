package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComplaintDelete is a Querydsl query type for ComplaintDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComplaintDelete extends EntityPathBase<ComplaintDelete> {

    private static final long serialVersionUID = -983568150L;

    public static final QComplaintDelete complaintDelete = new QComplaintDelete("complaintDelete");

    public final StringPath complaintUid = createString("complaintUid");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath targetUid = createString("targetUid");

    public final EnumPath<com.ArtSeeReal.pro.enums.ComplaintType> type = createEnum("type", com.ArtSeeReal.pro.enums.ComplaintType.class);

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public QComplaintDelete(String variable) {
        super(ComplaintDelete.class, forVariable(variable));
    }

    public QComplaintDelete(Path<? extends ComplaintDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaintDelete(PathMetadata metadata) {
        super(ComplaintDelete.class, metadata);
    }

}

