package model.dao;

import model.dao.base.JpaBaseDao;
import model.dao.commons.JpaUtils;


public class AbstractDao<T, K> extends JpaBaseDao<T, K> {

    public AbstractDao() {
        this.setEm(JpaUtils.getEm());
    }

}
