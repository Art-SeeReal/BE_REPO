package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComplaint is a Querydsl query type for Complaint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComplaint extends EntityPathBase<Complaint> {

    private static final long serialVersionUID = -1639976275L;

    public static final QComplaint complaint = new QComplaint("complaint");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath targetUid = createString("targetUid");

    public final EnumPath<com.ArtSeeReal.pro.enums.ComplaintType> type = createEnum("type", com.ArtSeeReal.pro.enums.ComplaintType.class);

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public QComplaint(String variable) {
        super(Complaint.class, forVariable(variable));
    }

    public QComplaint(Path<? extends Complaint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaint(PathMetadata metadata) {
        super(Complaint.class, metadata);
    }

}

