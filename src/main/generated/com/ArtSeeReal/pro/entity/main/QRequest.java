package com.ArtSeeReal.pro.entity.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequest is a Querydsl query type for Request
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequest extends EntityPathBase<Request> {

    private static final long serialVersionUID = -1684438767L;

    public static final QRequest request = new QRequest("request");

    public final com.ArtSeeReal.pro.entity.module.QRequestModule _super = new com.ArtSeeReal.pro.entity.module.QRequestModule(this);

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final BooleanPath secret = _super.secret;

    //inherited
    public final StringPath title = _super.title;

    //inherited
    public final NumberPath<Long> type = _super.type;

    public final StringPath uid = createString("uid");

    //inherited
    public final StringPath userUid = _super.userUid;

    public QRequest(String variable) {
        super(Request.class, forVariable(variable));
    }

    public QRequest(Path<? extends Request> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequest(PathMetadata metadata) {
        super(Request.class, metadata);
    }

}

