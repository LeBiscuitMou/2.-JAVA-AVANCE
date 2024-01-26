package net.ent.etrs.zoo.models.dao.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {
    private static EntityManager em;

    static {
        JpaUtil.em = Persistence.createEntityManagerFactory("pu-zoo").createEntityManager(); // pu name
    }

    public static EntityManager getEm() {
        return JpaUtil.em;
    }
}
