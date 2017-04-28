package com.sistema.restaurant.mirador.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcProveedor is a Querydsl query type for TcProveedor
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcProveedor extends EntityPathBase<TcProveedor> {

    private static final long serialVersionUID = 995517353L;

    public static final QTcProveedor tcProveedor = new QTcProveedor("tcProveedor");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath correo = createString("correo");

    public final StringPath direccion = createString("direccion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombreProveedor = createString("nombreProveedor");

    public final StringPath telefono = createString("telefono");

    public QTcProveedor(String variable) {
        super(TcProveedor.class, forVariable(variable));
    }

    public QTcProveedor(Path<? extends TcProveedor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcProveedor(PathMetadata<?> metadata) {
        super(TcProveedor.class, metadata);
    }

}

