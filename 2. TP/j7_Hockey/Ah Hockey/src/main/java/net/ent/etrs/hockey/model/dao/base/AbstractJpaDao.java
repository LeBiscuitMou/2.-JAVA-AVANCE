package net.ent.etrs.hockey.model.dao.base;

import net.ent.etrs.hockey.model.commons.JpaUtil;

public abstract class AbstractJpaDao<T, ID> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
