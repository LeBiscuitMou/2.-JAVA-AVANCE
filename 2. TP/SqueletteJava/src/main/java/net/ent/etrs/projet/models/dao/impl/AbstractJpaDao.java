package net.ent.etrs.projet.models.dao.impl;


import net.ent.etrs.projet.models.dao.utils.JpaUtil;
import net.ent.etrs.projet.models.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
