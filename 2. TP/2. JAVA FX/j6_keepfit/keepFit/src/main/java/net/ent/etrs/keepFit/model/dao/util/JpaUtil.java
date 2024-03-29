package net.ent.etrs.keepFit.model.dao.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JpaUtil {
    
    private static EntityManager em;
    
    static {
        JpaUtil.em = Persistence.createEntityManagerFactory("pu-keepfit").createEntityManager(); // Remplacer les ... par le nom du persistance unit
    }
    
    public static EntityManager getEm() {
        return JpaUtil.em;
    }
    
}
