package net.ent.etrs.championnathockey.models.daos.impl;

import net.ent.etrs.championnathockey.models.daos.DaoEquipe;
import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;

import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DaoEquipeImpl extends AbstractJpaDao<Equipe, Serializable> implements DaoEquipe {

    /**
     * Permet de retourner la liste des équipes d'une année de championnat
     *
     * @param annee Année de Championnat
     * @return Liste des équipes
     * @throws DaoException s'il y a eu un problème
     */
    @Override
    public Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) throws DaoException {
        try {
            TypedQuery<Equipe> tp = this.em.createQuery(
                    "SELECT e FROM Championnat c " +
                            "INNER JOIN c.classement e" +
                            " WHERE c.anneeChampionnat = :annee AND c.nomChampionnat = :nom", Equipe.class);
            tp.setParameter("annee", annee);
            tp.setParameter("nom", nomChampionnat);
            return new HashSet<>(tp.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Permet de trouver la meilleur equipe d'une Année de Championnat
     *
     * @param annee Année de Championnat
     * @return Soit l'équipe soit Null si aucun équipe n'a été trouvé
     * @throws DaoException s'il y a eu un problème
     *                      <p>
     *                      SQL :
     *                      SELECT e.ID,e.NOM,SUM(j.nb_point) NB_BUT FROM equipe e
     *                      INNER JOIN championnat c on c.id = e.championnat_id
     *                      LEFT JOIN joueur j on e.id = j.equipe_id
     *                      WHERE c.annee_championnat = ANNEE
     *                      GROUP BY e.id,e.NOM
     *                      ORDER BY NB_BUT desc
     */
    @Override
    public Optional<Equipe> findBestEquipeByYearAndName(Integer annee, String nomChampionnat) throws DaoException {
        try {
            TypedQuery<Equipe> tp = this.em.createQuery(
                    "SELECT e FROM Championnat c " +
                            "INNER JOIN c.classement e " +
                            "LEFT JOIN e.listeJoueurs j " +
                            "WHERE c.anneeChampionnat = :annee AND c.nomChampionnat = :nom" +
                            " GROUP BY e.id" +
                            " ORDER BY SUM(j.nbPoint) DESC", Equipe.class);
            tp.setParameter("annee", annee);
            tp.setParameter("nom", nomChampionnat);
            tp.setMaxResults(1);
            return Optional.of(tp.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }


    /**
     * Permet de retourner le dictionnaire des meilleurs joueurs par Equipe d'une Année de Championnat
     *
     * @param annee Année de Championnat
     * @return une Map<Equipe, Joueur> contenant le meilleur joueur par Equipe pour l'année de championnat
     * <p>
     * SQL :
     * SELECT e.nom,j.nom  FROM equipe e
     * INNER JOIN championnat c on c.id = e.championnat_id
     * LEFT JOIN joueur j on e.id = j.equipe_id
     * WHERE c.annee_championnat = 2022
     * AND j.nb_point = (SELECT MAX(j2.nb_point) FROM joueur j2 WHERE e.id = j2.equipe_id)
     */
    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) throws DaoException {
        try {
            Map<Equipe, Joueur> tp = this.em.createQuery(
                            "SELECT e as equipe,j as joueur FROM Championnat c " +
                                    "INNER JOIN c.classement e " +
                                    "INNER JOIN e.listeJoueurs j " +
                                    "WHERE c.anneeChampionnat = :annee AND c.nomChampionnat = :nom" +
                                    " AND j.nbPoint = (SELECT MAX(j2.nbPoint) FROM e.listeJoueurs j2) ", Tuple.class)
                    .setParameter("annee", annee).setParameter("nom", nomChampionnat)
                    .getResultStream()
                    .collect(Collectors.toMap(
                            tuple -> ((Equipe) tuple.get("equipe")),
                            tuple -> ((Joueur) tuple.get("joueur"))
                    ));
            return tp;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Equipe> findByName(String name) {
        return Optional.empty();
    }

}
