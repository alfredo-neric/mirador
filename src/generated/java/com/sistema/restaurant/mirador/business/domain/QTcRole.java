package com.sistema.restaurant.mirador.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcRole is a Querydsl query type for TcRole
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcRole extends EntityPathBase<TcRole> {

    private static final long serialVersionUID = -1489742645L;

    public static final QTcRole tcRole = new QTcRole("tcRole");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clave = createString("clave");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<TcMenus, QTcMenus> menus = this.<TcMenus, QTcMenus>createList("menus", TcMenus.class, QTcMenus.class, PathInits.DIRECT2);

    public final StringPath nombre = createString("nombre");

    public QTcRole(String variable) {
        super(TcRole.class, forVariable(variable));
    }

    public QTcRole(Path<? extends TcRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcRole(PathMetadata<?> metadata) {
        super(TcRole.class, metadata);
    }

}

