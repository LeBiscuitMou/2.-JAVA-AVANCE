package net.ent.etrs.heartStone.models.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.heartStone.models.dao.CarteDao;
import net.ent.etrs.heartStone.models.dao.jdbc.impl.CarteDaoJDBCImpl;
import net.ent.etrs.heartStone.models.dao.mem.impl.CarteDaoImpl;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

   /**
       * Permet de créer un sigleton de carteDaoInstance.
       */
       private static CarteDao carteDaoInstance;

       public static CarteDao fabriquerCarteDao() {
           if (Objects.isNull(carteDaoInstance)) {
               carteDaoInstance = new CarteDaoJDBCImpl();
           }
           return carteDaoInstance;
       }


}
