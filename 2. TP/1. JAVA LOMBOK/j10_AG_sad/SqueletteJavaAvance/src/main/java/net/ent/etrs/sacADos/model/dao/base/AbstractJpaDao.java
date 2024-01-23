package net.ent.etrs.sacADos.model.dao.base;

import net.ent.etrs.sacADos.model.commons.JpaUtil;

public abstract class AbstractJpaDao<T, ID> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
