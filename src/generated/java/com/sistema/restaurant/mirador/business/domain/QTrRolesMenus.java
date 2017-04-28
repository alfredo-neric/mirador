package com.sistema.restaurant.mirador.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTrRolesMenus is a Querydsl query type for TrRolesMenus
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTrRolesMenus extends EntityPathBase<TrRolesMenus> {

    private static final long serialVersionUID = 1883684891L;

    public static final QTrRolesMenus trRolesMenus = new QTrRolesMenus("trRolesMenus");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idMenu = createNumber("idMenu", Long.class);

    public final NumberPath<Long> idRol = createNumber("idRol", Long.class);

    public QTrRolesMenus(String variable) {
        super(TrRolesMenus.class, forVariable(variable));
    }

    public QTrRolesMenus(Path<? extends TrRolesMenus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrRolesMenus(PathMetadata<?> metadata) {
        super(TrRolesMenus.class, metadata);
    }

}

