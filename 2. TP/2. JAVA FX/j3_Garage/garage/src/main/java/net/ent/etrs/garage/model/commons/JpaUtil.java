package net.ent.etrs.garage.model.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JpaUtil {
    
    private static EntityManager em;
    
    static {
        JpaUtil.em = Persistence.createEntityManagerFactory("pu-garage").createEntityManager(); // Remplacer les ... par le nom du persistance unit
    }
    
    public static EntityManager getEm() {
        return JpaUtil.em;
    }
    
}
