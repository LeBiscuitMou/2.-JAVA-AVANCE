package net.ent.etrs.logistock.model.dao.base;

import net.ent.etrs.logistock.model.commons.JpaUtil;

public abstract class AbstractJpaDao<T, ID> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
