package net.ent.etrs.projet.models.dao.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {
    private static final String PERSIST_UNIT_NAME = "pu";
    private static EntityManager em;

    static {
        JpaUtil.em = Persistence.createEntityManagerFactory(PERSIST_UNIT_NAME).createEntityManager(); // pu name
    }

    public static EntityManager getEm() {
        return JpaUtil.em;
    }
}