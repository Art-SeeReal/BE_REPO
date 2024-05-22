package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComplaintModule is a Querydsl query type for ComplaintModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QComplaintModule extends EntityPathBase<ComplaintModule> {

    private static final long serialVersionUID = -2030399380L;

    public static final QComplaintModule complaintModule = new QComplaintModule("complaintModule");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath targetUid = createString("targetUid");

    public final EnumPath<com.ArtSeeReal.pro.enums.ComplaintType> type = createEnum("type", com.ArtSeeReal.pro.enums.ComplaintType.class);

    public final StringPath userUid = createString("userUid");

    public QComplaintModule(String variable) {
        super(ComplaintModule.class, forVariable(variable));
    }

    public QComplaintModule(Path<? extends ComplaintModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaintModule(PathMetadata metadata) {
        super(ComplaintModule.class, metadata);
    }

}

