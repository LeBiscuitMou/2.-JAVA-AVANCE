package net.ent.etrs.projetjavamaier.models.daos.impl;

import net.ent.etrs.projetjavamaier.models.daos.ConsoleDao;
import net.ent.etrs.projetjavamaier.models.daos.base.JpaBaseDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.Console;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.comparator.ComparatorJeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.ConstantesMetier;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;

import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


public class DaoConsoleImpl extends JpaBaseDao<Console> implements ConsoleDao {

    @Override
    public Console getLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws DaoException {
        try {
            TypedQuery<Console> query = this.em.createQuery("SELECT c FROM Console c " +
                            "WHERE KEY(c.sorties) =: pays " +
                            "AND VALUE(c.sorties) = (select MIN (value(c2.sorties) )FROM Console c2) ", Console.class)
                    .setParameter("pays", pays);

            return query.getSingleResult();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws DaoException {
        try {

            List<Tuple> query1 = this.em.createQuery("SELECT j FROM JeuVideo j JOIN Console  c WHERE j.nom =:nom AND c.nom =:nom", Tuple.class).getResultList();
            Map<Console, List<JeuVideo>> query = query1.stream()
                    .collect(Collectors.groupingBy(
                    tuple -> (tuple.get(0, Console.class)),
                    Collectors.mapping(tuple -> tuple.get(1, JeuVideo.class), Collectors.toList())
            ));

            return query;

        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }




    @Override
    public String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws DaoException {

        try {
            List<JeuVideo> jeux = new ArrayList<>(recupererConsoleAvecLeurJeuxVideo().get(console));
            if (jeux.size() < 2) {
                throw new DaoException(ConstantesMetier.ERR_JEUX_VIDEO_CONSOLE_MIN);
            }
              jeux.sort(new ComparatorJeuVideo());
            long joursTotal = 0;
            for (int i = 0; i < jeux.size()-1; i++) {
                  long joursEntre = DAYS.between(jeux.get(i).getDateSortie(), jeux.get(i +1).getDateSortie());
                joursTotal += joursEntre;
            }
            long moyenneJours = joursTotal / (jeux.size()-1);
                 Period periodMoyenne = Period.ofDays((int) moyenneJours);

            return periodMoyenne.getYears() + " annees " +periodMoyenne.getMonths()  + " mois " +periodMoyenne.getDays() + " jours";
        } catch (IllegalArgumentException | PersistenceException e) {

            throw new DaoException(e.getMessage(), e);
        }
    }
    //J'ai rajouter cette methode car je n'ai pas trouver d'autre solution pour l'init
    @Override
    public Console findByName(String s) throws DaoException {
        try{
            TypedQuery<Console> query = this.em.createQuery("SELECT c FROM Console c WHERE c.nom =: nom", Console.class)
                   .setParameter("nom", s);

            return query.getSingleResult();

        }catch (IllegalArgumentException | PersistenceException e) {

            throw new DaoException(e.getMessage(), e);
    }
}}
