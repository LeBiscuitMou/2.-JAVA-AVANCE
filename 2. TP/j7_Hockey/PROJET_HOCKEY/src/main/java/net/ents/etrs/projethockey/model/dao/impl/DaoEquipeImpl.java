package net.ents.etrs.projethockey.model.dao.impl;

import net.ents.etrs.projethockey.model.dao.IDaoEquipe;
import net.ents.etrs.projethockey.model.dao.base.JpaBaseDao;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;
import net.ents.etrs.projethockey.model.facade.exceptions.BusinessException;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.*;

public class DaoEquipeImpl extends JpaBaseDao<Equipe> implements IDaoEquipe {


    @Override
    public Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) {
        try {
            // Utilisation de Query
            Query query = em.createQuery(
                    "SELECT e FROM Equipe e " +
                            "JOIN Championnat c " +
                            "WHERE c.nomChampionnat = :nomChampionnat AND c.anneeChampionnat = :annee");

            // Définition des paramètres
            query.setParameter("nomChampionnat", nomChampionnat);
            query.setParameter("annee", annee);

            // Exécution de la requête et récupération du résultat
            List<?> resultList = query.getResultList();

            // Conversion de la liste en Set<Equipe>
            Set<Equipe> equipes = new HashSet<>();
            for (Object o : resultList) {
                if (o instanceof Equipe) {
                    equipes.add((Equipe) o);
                }
            }
            return equipes;
        } catch (PersistenceException e) {
            // Gestion des exceptions
            throw new RuntimeException("Erreur lors de la récupération des équipes", e);
        }
    }

    @Override
    public Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) {
        try {
            // Utilisation de Query
            Query query = em.createQuery(
                    "SELECT e FROM Equipe e " +
                            "JOIN Championnat c " +
                            "WHERE c.nomChampionnat = :nomChampionnat AND c.anneeChampionnat = :annee");

            // Définition des paramètres
            query.setParameter("nomChampionnat", nomChampionnat);
            query.setParameter("annee", annee);

            // Exécution de la requête et récupération du résultat
            List<?> resultList = query.getResultList();

            // Conversion de la liste en Set<Equipe>
            Set<Equipe> equipes = new HashSet<>();
            for (Object o : resultList) {
                if (o instanceof Equipe) {
                    equipes.add((Equipe) o);
                }
            }
            return equipes.stream().max((e1, e2) -> {
                int result = e2.getTotalPoints() - e1.getTotalPoints();
                if (result == 0) {
                    result = e1.getNom().compareTo(e2.getNom());
                }
                return result;
            }).orElse(null);
        } catch (PersistenceException e) {
            // Gestion des exceptions
            throw new RuntimeException("Erreur lors de la récupération des équipes", e);
        }
    }

    @Override
    public Equipe findEquipeByName(String name) throws DaoException {
        try {
            // Utilisation de Query
            Query query = em.createQuery(
                    "SELECT e FROM Equipe e " +
                            "WHERE e.nom = :name");

            // Définition des paramètres
            query.setParameter("name", name);

            // Exécution de la requête et récupération du résultat
            List<?> resultList = query.getResultList();

            // Conversion de la liste en Set<Equipe>
            Set<Equipe> equipes = new HashSet<>();
            for (Object o : resultList) {
                if (o instanceof Equipe) {
                    equipes.add((Equipe) o);
                }
            }
            return equipes.stream().findFirst().orElse(null);
        } catch (PersistenceException e) {
            // Gestion des exceptions
            throw new RuntimeException("Erreur lors de la récupération des équipes", e);
        }
    }

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

    protected static Map<Equipe, Joueur> getEquipeJoueurMap(Query query) {
        List<?> resultList = query.getResultList();

        // Conversion du résultat en Map<Equipe, Joueur>
        Map<Equipe, Joueur> resultMap = new HashMap<>();
        for (Object o : resultList) {
            if (o instanceof Object[] objArray) {
                if (objArray.length == 2) {
                    Equipe equipe = (Equipe) objArray[0];
                    Joueur joueur = (Joueur) objArray[1];
                    resultMap.put(equipe, joueur);
                }
            }
        }
        return resultMap;
    }


}
