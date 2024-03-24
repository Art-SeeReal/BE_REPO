package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 371836474L;

    public static final QFile file = new QFile("file");

    public final com.ArtSeeReal.pro.entity.module.QFileModule _super = new com.ArtSeeReal.pro.entity.module.QFileModule(this);

    //inherited
    public final StringPath targetUid = _super.targetUid;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath url = _super.url;

    public QFile(String variable) {
        super(File.class, forVariable(variable));
    }

    public QFile(Path<? extends File> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFile(PathMetadata metadata) {
        super(File.class, metadata);
    }

}

