package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIntroduceHistory is a Querydsl query type for IntroduceHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIntroduceHistory extends EntityPathBase<IntroduceHistory> {

    private static final long serialVersionUID = 199974918L;

    public static final QIntroduceHistory introduceHistory = new QIntroduceHistory("introduceHistory");

    public final StringPath introduceUid = createString("introduceUid");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final StringPath newContent = createString("newContent");

    public final StringPath oldContent = createString("oldContent");

    public final StringPath uid = createString("uid");

    public final StringPath userUid = createString("userUid");

    public QIntroduceHistory(String variable) {
        super(IntroduceHistory.class, forVariable(variable));
    }

    public QIntroduceHistory(Path<? extends IntroduceHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIntroduceHistory(PathMetadata metadata) {
        super(IntroduceHistory.class, metadata);
    }

}

