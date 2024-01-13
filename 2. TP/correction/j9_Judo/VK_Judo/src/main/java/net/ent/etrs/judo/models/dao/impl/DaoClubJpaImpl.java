package net.ent.etrs.judo.models.dao.impl;

import net.ent.etrs.judo.models.dao.IDaoClub;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Club;
import net.ent.etrs.judo.models.entities.references.Ville;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DaoClubJpaImpl extends JpaBaseDao<Club> implements IDaoClub {

    /**
     * Trouve le club ayant le nom voulu et qui ont au moins 1 judoka.
     *
     * @param pName le nom du club
     * @return le club voulu
     * @throws DaoException erreur BDD
     */
    @Override
    public Optional<Club> findClubByNomWithJudokas(String pName) throws DaoException {
        try {
            TypedQuery<Club> query = this.em.createQuery
                    ("SELECT cl " +
                                    "FROM Club cl " +
                                    "WHERE cl.nom = :nom AND cl.membres IS NOT EMPTY"
                            , Club.class);
            query.setParameter("nom", pName);

            List<Club> list = query.getResultList();
            if(list.isEmpty()){
                return Optional.empty();
            }
            return Optional.ofNullable(list.get(0));

        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Trouve tous les clubs basés dans la ville choisis
     *
     * @param pVille la ville choisis
     * @return une liste de clubs
     * @throws DaoException erreur BDD
     */
    @Override
    public List<Club> findAllClubByVille(Ville pVille) throws DaoException {
        try {
            TypedQuery<Club> query = this.em.createQuery("SELECT t " +
                            "FROM Club t " +
                            "WHERE t.ville = :ville"
                    , Club.class);
            query.setParameter("ville", pVille);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }
}
