package net.ent.etrs.conso_elec_gaz.model.daos.impl;




import com.sun.xml.bind.v2.model.core.ID;
import net.ent.etrs.conso_elec_gaz.model.commons.utils.JpaUtil;
import net.ent.etrs.conso_elec_gaz.model.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity> extends JpaBaseDao<T> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
