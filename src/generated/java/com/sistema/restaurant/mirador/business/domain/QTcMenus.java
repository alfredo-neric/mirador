package com.sistema.restaurant.mirador.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcMenus is a Querydsl query type for TcMenus
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcMenus extends EntityPathBase<TcMenus> {

    private static final long serialVersionUID = 1057705279L;

    public static final QTcMenus tcMenus = new QTcMenus("tcMenus");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath label = createString("label");

    public final ListPath<TcMenuItem, QTcMenuItem> menuItems = this.<TcMenuItem, QTcMenuItem>createList("menuItems", TcMenuItem.class, QTcMenuItem.class, PathInits.DIRECT2);

    public final StringPath styleClass = createString("styleClass");

    public final StringPath url = createString("url");

    public QTcMenus(String variable) {
        super(TcMenus.class, forVariable(variable));
    }

    public QTcMenus(Path<? extends TcMenus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcMenus(PathMetadata<?> metadata) {
        super(TcMenus.class, metadata);
    }

}

