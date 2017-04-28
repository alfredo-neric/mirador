package com.sistema.restaurant.mirador.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcStatus is a Querydsl query type for TcStatus
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcStatus extends EntityPathBase<TcStatus> {

    private static final long serialVersionUID = -1385635065L;

    public static final QTcStatus tcStatus = new QTcStatus("tcStatus");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<java.math.BigInteger> idStatus = createNumber("idStatus", java.math.BigInteger.class);

    public QTcStatus(String variable) {
        super(TcStatus.class, forVariable(variable));
    }

    public QTcStatus(Path<? extends TcStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcStatus(PathMetadata<?> metadata) {
        super(TcStatus.class, metadata);
    }

}

