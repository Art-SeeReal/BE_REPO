package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIntroduceDelete is a Querydsl query type for IntroduceDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIntroduceDelete extends EntityPathBase<IntroduceDelete> {

    private static final long serialVersionUID = -1842063778L;

    public static final QIntroduceDelete introduceDelete = new QIntroduceDelete("introduceDelete");

    public final com.ArtSeeReal.pro.entity.module.QIntroduceModule _super = new com.ArtSeeReal.pro.entity.module.QIntroduceModule(this);

    //inherited
    public final StringPath content = _super.content;

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath introduceUid = createString("introduceUid");

    public final StringPath uid = createString("uid");

    public QIntroduceDelete(String variable) {
        super(IntroduceDelete.class, forVariable(variable));
    }

    public QIntroduceDelete(Path<? extends IntroduceDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIntroduceDelete(PathMetadata metadata) {
        super(IntroduceDelete.class, metadata);
    }

}

