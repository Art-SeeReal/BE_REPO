package com.ArtSeeReal.pro.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileHistory is a Querydsl query type for FileHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileHistory extends EntityPathBase<FileHistory> {

    private static final long serialVersionUID = 1625198407L;

    public static final QFileHistory fileHistory = new QFileHistory("fileHistory");

    public final com.ArtSeeReal.pro.entity.module.QFileModule _super = new com.ArtSeeReal.pro.entity.module.QFileModule(this);

    public final StringPath exUrl = createString("exUrl");

    public final StringPath fileUid = createString("fileUid");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    //inherited
    public final StringPath targetUid = _super.targetUid;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath url = _super.url;

    public QFileHistory(String variable) {
        super(FileHistory.class, forVariable(variable));
    }

    public QFileHistory(Path<? extends FileHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileHistory(PathMetadata metadata) {
        super(FileHistory.class, metadata);
    }

}

