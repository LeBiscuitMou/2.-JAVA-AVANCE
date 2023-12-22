package net.ent.etrs.leagueJPA.start;

import net.ent.etrs.leagueJPA.models.commons.validator.ValidException;
import net.ent.etrs.leagueJPA.models.dao.DaoFactory;
import net.ent.etrs.leagueJPA.models.dao.PersonnageDao;
import net.ent.etrs.leagueJPA.models.dao.exceptions.DaoException;
import net.ent.etrs.leagueJPA.models.entities.EntitiesFactory;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;

import java.sql.SQLException;

public class Lanceur {
    public static void main(String[] args) {
        PersonnageDao personnageDao = DaoFactory.getDaoPersonnage();
        try {
            personnageDao.save(EntitiesFactory.fabriquerPersonnage("BITE", LabySpecialite.BERSERKER, 8));

            personnageDao.findAll().forEach(System.out::println);
        } catch (DaoException | ValidException | SQLException e) {
            e.printStackTrace();
        }
    }
}