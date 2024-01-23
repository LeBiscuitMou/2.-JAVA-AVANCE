package net.ent.etrs.consoElecgaz.models.daos.impl;


import net.ent.etrs.consoElecgaz.commons.utils.JpaUtil;
import net.ent.etrs.consoElecgaz.models.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
