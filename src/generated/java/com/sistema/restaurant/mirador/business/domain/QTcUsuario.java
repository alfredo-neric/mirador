package com.sistema.restaurant.mirador.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcUsuario is a Querydsl query type for TcUsuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcUsuario extends EntityPathBase<TcUsuario> {

    private static final long serialVersionUID = 1759265465L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTcUsuario tcUsuario = new QTcUsuario("tcUsuario");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> accountnonexpired = createNumber("accountnonexpired", Integer.class);

    public final NumberPath<Integer> accountnonlocked = createNumber("accountnonlocked", Integer.class);

    public final StringPath contrasenia = createString("contrasenia");

    public final NumberPath<Integer> credentialsnonexpired = createNumber("credentialsnonexpired", Integer.class);

    public final DateTimePath<java.sql.Timestamp> fechaCreacion = createDateTime("fechaCreacion", java.sql.Timestamp.class);

    public final NumberPath<Integer> habilitado = createNumber("habilitado", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombre = createString("nombre");

    public final QTcRole role;

    public final StringPath usuario = createString("usuario");

    public QTcUsuario(String variable) {
        this(TcUsuario.class, forVariable(variable), INITS);
    }

    public QTcUsuario(Path<? extends TcUsuario> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTcUsuario(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTcUsuario(PathMetadata<?> metadata, PathInits inits) {
        this(TcUsuario.class, metadata, inits);
    }

    public QTcUsuario(Class<? extends TcUsuario> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.role = inits.isInitialized("role") ? new QTcRole(forProperty("role")) : null;
    }

}

