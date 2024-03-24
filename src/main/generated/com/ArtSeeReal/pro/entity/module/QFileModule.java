package com.ArtSeeReal.pro.entity.module;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileModule is a Querydsl query type for FileModule
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QFileModule extends EntityPathBase<FileModule> {

    private static final long serialVersionUID = -813713293L;

    public static final QFileModule fileModule = new QFileModule("fileModule");

    public final StringPath targetUid = createString("targetUid");

    public final StringPath url = createString("url");

    public QFileModule(String variable) {
        super(FileModule.class, forVariable(variable));
    }

    public QFileModule(Path<? extends FileModule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileModule(PathMetadata metadata) {
        super(FileModule.class, metadata);
    }

}

