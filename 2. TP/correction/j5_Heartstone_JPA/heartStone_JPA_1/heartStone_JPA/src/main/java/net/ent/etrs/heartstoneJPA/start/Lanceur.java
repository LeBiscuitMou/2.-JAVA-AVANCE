package net.ent.etrs.heartstoneJPA.start;

import net.ent.etrs.heartstoneJPA.models.commons.validator.ValidException;
import net.ent.etrs.heartstoneJPA.models.dao.CarteDao;
import net.ent.etrs.heartstoneJPA.models.dao.DaoFactory;
import net.ent.etrs.heartstoneJPA.models.dao.exceptions.DaoException;
import net.ent.etrs.heartstoneJPA.models.entities.EntitiesFactory;
import net.ent.etrs.heartstoneJPA.models.entities.references.Classe;
import net.ent.etrs.heartstoneJPA.models.entities.references.TypeCarte;

import java.sql.SQLException;
import java.time.LocalDate;

public class Lanceur {
    public static void main(String[] args) {

        try {
            CarteDao dao = DaoFactory.getDaoCarte();

            dao.save(EntitiesFactory.fabriquerCarte("TEST",4, LocalDate.now().minusDays(6), TypeCarte.ELEM, Classe.DRUIDE,null));


            dao.findAll().forEach(System.out::println);
        } catch (DaoException | SQLException | ValidException e) {
            throw new RuntimeException(e);
        }
    }
}
