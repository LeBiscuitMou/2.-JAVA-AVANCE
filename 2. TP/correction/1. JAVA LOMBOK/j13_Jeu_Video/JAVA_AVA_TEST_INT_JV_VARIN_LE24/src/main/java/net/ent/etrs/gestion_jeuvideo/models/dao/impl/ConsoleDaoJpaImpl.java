package net.ent.etrs.gestion_jeuvideo.models.dao.impl;

import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoConsole;
import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;

import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ConsoleDaoJpaImpl extends JpaBaseDao<Console> implements IDaoConsole {

    /**
     * Permet de récupérer la console qui est la plus vieille pour un pays.
     *
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     * @throws DaoException l'exception lancée
     */
    @Override
    public Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws DaoException {
        try {
            // sous requête pour récupérer la date de sortie la plus ancienne d'un pays
            TypedQuery<LocalDate> sousQuery = this.em.createQuery("""
                            SELECT MIN(VALUE(sor))
                                FROM Console co INNER JOIN co.sorties sor
                                WHERE KEY(sor) = :pays
                                GROUP BY KEY(sor)"""
                    , LocalDate.class);
            sousQuery.setParameter("pays", pays);
            sousQuery.setMaxResults(1);

            LocalDate laDate = sousQuery.getSingleResult();

            TypedQuery<Console> query = this.em.createQuery("""
                            SELECT co
                            FROM Console co INNER JOIN co.sorties sor
                            WHERE KEY(sor) = :pays
                                AND :laDate MEMBER OF sor"""
                    , Console.class);
            query.setParameter("pays", pays);
            query.setParameter("laDate", laDate);

            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Permet de récupérer un dictionnaire contenant toutes les consoles avec leurs jeux video.
     *
     * @return une map contenant toutes les consoles avec leurs jeux video
     * @throws DaoException l'exception lancée
     */
    @Override
    public Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws DaoException {
        try {
            TypedQuery<Tuple> query = this.em.createQuery("""
                            SELECT co AS console, jv AS jeu
                            FROM JeuVideo jv LEFT JOIN jv.plateformes co"""
                    , Tuple.class);

            Map<Console, List<JeuVideo>> resultMap = new TreeMap<>();

            query.getResultStream().forEach(tuple -> {
                resultMap.putIfAbsent((Console) tuple.get("console"), new ArrayList<>());
                resultMap.get((Console) tuple.get("console")).add((JeuVideo) tuple.get("jeu"));
            });

            return resultMap;
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Console> findConsoleByIdentity(String nomConsole) throws DaoException {
        try {
            TypedQuery<Console> query = this.em.createQuery("""
                            SELECT t
                            FROM Console t
                            WHERE t.nom = :nomConsole"""
                    , Console.class);
            query.setParameter("nomConsole", nomConsole);

            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}