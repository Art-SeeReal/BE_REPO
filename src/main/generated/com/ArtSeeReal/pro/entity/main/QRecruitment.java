package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitment is a Querydsl query type for Recruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitment extends EntityPathBase<Recruitment> {

    private static final long serialVersionUID = 738544414L;

    public static final QRecruitment recruitment = new QRecruitment("recruitment");

    public final com.ArtSeeReal.pro.entity.module.QRecruitmentModule _super = new com.ArtSeeReal.pro.entity.module.QRecruitmentModule(this);

    //inherited
    public final EnumPath<com.ArtSeeReal.pro.enums.CategoryType> category = _super.category;

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dueDate = _super.dueDate;

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

    public QRecruitment(String variable) {
        super(Recruitment.class, forVariable(variable));
    }

    public QRecruitment(Path<? extends Recruitment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitment(PathMetadata metadata) {
        super(Recruitment.class, metadata);
    }

}

