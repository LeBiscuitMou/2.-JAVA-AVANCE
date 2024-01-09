package net.ents.etrs.projethockey.model.dao.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtils {

    private static EntityManager em;

    public static EntityManager getEm(String unitName) {
        if (em == null || !em.isOpen()) {
            em = Persistence.createEntityManagerFactory(unitName).createEntityManager();
        }
        return em;
    }
}
