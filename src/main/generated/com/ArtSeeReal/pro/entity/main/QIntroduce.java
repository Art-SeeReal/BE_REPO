package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIntroduce is a Querydsl query type for Introduce
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIntroduce extends EntityPathBase<Introduce> {

    private static final long serialVersionUID = 635076385L;

    public static final QIntroduce introduce = new QIntroduce("introduce");

    public final com.ArtSeeReal.pro.entity.module.QIntroduceModule _super = new com.ArtSeeReal.pro.entity.module.QIntroduceModule(this);

    //inherited
    public final StringPath content = _super.content;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    public QIntroduce(String variable) {
        super(Introduce.class, forVariable(variable));
    }

    public QIntroduce(Path<? extends Introduce> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIntroduce(PathMetadata metadata) {
        super(Introduce.class, metadata);
    }

}

