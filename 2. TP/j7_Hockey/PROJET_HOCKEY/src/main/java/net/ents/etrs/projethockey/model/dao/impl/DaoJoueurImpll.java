package net.ents.etrs.projethockey.model.dao.impl;

import net.ents.etrs.projethockey.model.dao.IDaoJoueur;
import net.ents.etrs.projethockey.model.dao.base.JpaBaseDao;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.*;

import static net.ents.etrs.projethockey.model.dao.impl.DaoEquipeImpl.getEquipeJoueurMap;

public class DaoJoueurImpll extends JpaBaseDao<Joueur> implements IDaoJoueur {
    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) {
        try {
            // Utilisation de Query
            Query query = em.createQuery(
                    "SELECT e, j FROM Equipe e " +
                            "JOIN Joueur j " +
                            "JOIN Championnat c " +
                            "WHERE c.nomChampionnat = :nomChampionnat AND c.anneeChampionnat = :annee");

            // Définition des paramètres
            query.setParameter("nomChampionnat", nomChampionnat);
            query.setParameter("annee", annee);

            // Exécution de la requête et récupération du résultat
            return getEquipeJoueurMap(query);
        } catch (PersistenceException e) {
            // Gestion des exceptions
            throw new RuntimeException("Erreur lors de la récupération des joueurs par équipe", e);
        }
    }

    @Override
    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) {
        //Utilisation de Query
        Query query = em.createQuery("SELECT j FROM Joueur j WHERE j.dateNaissance < :date", Joueur.class);

        //Definition des paramètres
        query.setParameter("date", date);

        List list = query.getResultList();

        Set<Joueur> joueurs = new HashSet<>(list);

        return joueurs;
    }


    @Override
    public Joueur findJoueurBestScoreur() {
        // Utilisation de TypedQuery pour obtenir un objet Joueur
        TypedQuery<Joueur> query = em.createQuery(
                "SELECT j FROM Joueur j JOIN Match m " +
                        "WHERE m.score = (SELECT MAX(m.score) FROM Match m)",
                Joueur.class);

        try {
            // Retourne le joueur avec le meilleur score
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}

