package model.dao.base;

import model.commons.JpaUtil;

public abstract class AbstractJpaDao<T, ID> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
