package com.ArtSeeReal.pro.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApplyRecruitmentKey is a Querydsl query type for ApplyRecruitmentKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QApplyRecruitmentKey extends BeanPath<ApplyRecruitmentKey> {

    private static final long serialVersionUID = 1451257651L;

    public static final QApplyRecruitmentKey applyRecruitmentKey = new QApplyRecruitmentKey("applyRecruitmentKey");

    public final StringPath recruitmentUid = createString("recruitmentUid");

    public final StringPath userUid = createString("userUid");

    public QApplyRecruitmentKey(String variable) {
        super(ApplyRecruitmentKey.class, forVariable(variable));
    }

    public QApplyRecruitmentKey(Path<? extends ApplyRecruitmentKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplyRecruitmentKey(PathMetadata metadata) {
        super(ApplyRecruitmentKey.class, metadata);
    }

}

