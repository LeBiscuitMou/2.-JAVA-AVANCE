package net.ent.etrs.heartStone.models.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.heartStone.models.dao.CarteDao;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

   /**
       * Permet de créer un sigleton de carteDaoInstance.
       */
       private static CarteDao carteDaoInstance;

       public static CarteDao fabriquerCarteDao() {
           if (Objects.isNull(carteDaoInstance)) {
               carteDaoInstance = new CarteDaoImpl();
           }
           return carteDaoInstance;
       }


}
