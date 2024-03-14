package com.ArtSeeReal.pro.entity.delete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileDelete is a Querydsl query type for FileDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileDelete extends EntityPathBase<FileDelete> {

    private static final long serialVersionUID = 1057360659L;

    public static final QFileDelete fileDelete = new QFileDelete("fileDelete");

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath delUserUid = createString("delUserUid");

    public final StringPath fileUid = createString("fileUid");

    public final StringPath targetUid = createString("targetUid");

    public final StringPath uid = createString("uid");

    public final StringPath url = createString("url");

    public QFileDelete(String variable) {
        super(FileDelete.class, forVariable(variable));
    }

    public QFileDelete(Path<? extends FileDelete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileDelete(PathMetadata metadata) {
        super(FileDelete.class, metadata);
    }

}

