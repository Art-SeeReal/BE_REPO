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

    public final StringPath fileUid = createString("fileUid");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserUid = createString("modUserUid");

    public final ArrayPath<byte[], Byte> newImageData = createArray("newImageData", byte[].class);

    public final ArrayPath<byte[], Byte> oldImageData = createArray("oldImageData", byte[].class);

    public final StringPath targetUid = createString("targetUid");

    public final StringPath uid = createString("uid");

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

