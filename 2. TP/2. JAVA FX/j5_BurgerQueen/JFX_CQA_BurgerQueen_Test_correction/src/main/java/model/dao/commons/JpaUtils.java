package model.dao.commons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtils {

    @Getter
    private static final EntityManager em;

    static {
        em = Persistence.createEntityManagerFactory("JFX_CQA_BurgerQueen").createEntityManager();
    }

}
