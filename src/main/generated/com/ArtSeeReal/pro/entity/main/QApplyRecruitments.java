package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplyRecruitments is a Querydsl query type for ApplyRecruitments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplyRecruitments extends EntityPathBase<ApplyRecruitments> {

    private static final long serialVersionUID = 1060822247L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplyRecruitments applyRecruitments = new QApplyRecruitments("applyRecruitments");

    public final com.ArtSeeReal.pro.entity.composite.QApplyRecruitmentKey pk;

    public QApplyRecruitments(String variable) {
        this(ApplyRecruitments.class, forVariable(variable), INITS);
    }

    public QApplyRecruitments(Path<? extends ApplyRecruitments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplyRecruitments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplyRecruitments(PathMetadata metadata, PathInits inits) {
        this(ApplyRecruitments.class, metadata, inits);
    }

    public QApplyRecruitments(Class<? extends ApplyRecruitments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new com.ArtSeeReal.pro.entity.composite.QApplyRecruitmentKey(forProperty("pk")) : null;
    }

}

