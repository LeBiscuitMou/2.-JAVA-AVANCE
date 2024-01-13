package net.ent.etrs.squelette.model.dao.base;

import net.ent.etrs.squelette.model.commons.JpaUtil;

public abstract class AbstractJpaDao<T, ID> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
