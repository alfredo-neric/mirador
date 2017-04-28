package com.sistema.restaurant.mirador.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTwUserAttempt is a Querydsl query type for TwUserAttempt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTwUserAttempt extends EntityPathBase<TwUserAttempt> {

    private static final long serialVersionUID = 1976709625L;

    public static final QTwUserAttempt twUserAttempt = new QTwUserAttempt("twUserAttempt");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> attempts = createNumber("attempts", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> lastmodified = createDateTime("lastmodified", java.util.Date.class);

    public final StringPath usuario = createString("usuario");

    public QTwUserAttempt(String variable) {
        super(TwUserAttempt.class, forVariable(variable));
    }

    public QTwUserAttempt(Path<? extends TwUserAttempt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTwUserAttempt(PathMetadata<?> metadata) {
        super(TwUserAttempt.class, metadata);
    }

}

