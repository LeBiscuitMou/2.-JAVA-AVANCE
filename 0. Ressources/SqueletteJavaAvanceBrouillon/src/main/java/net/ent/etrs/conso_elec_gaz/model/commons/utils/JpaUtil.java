package net.ent.etrs.conso_elec_gaz.model.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {
    private static EntityManager em;

    static {
        JpaUtil.em = Persistence.createEntityManagerFactory("pu-conso").createEntityManager(); // pu name
    }

    public static EntityManager getEm() {
        return JpaUtil.em;
    }
}
