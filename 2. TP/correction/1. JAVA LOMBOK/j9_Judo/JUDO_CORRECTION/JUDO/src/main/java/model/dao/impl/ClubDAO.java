package model.dao.impl;

import model.dao.IClubDAO;
import model.dao.base.AbstractJpaDao;
import model.entities.Club;
import model.entities.references.Ville;
import model.exceptions.DaoException;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ClubDAO extends AbstractJpaDao<Club, Long> implements IClubDAO {
    @Override
    public Optional<Club> findClubByNomWithJudokas(String pName) throws DaoException {
        TypedQuery<Club> q = em.createQuery("SELECT c FROM Club c LEFT JOIN FETCH c.membres WHERE c.nom=:nom", Club.class);
        q.setParameter("nom", pName);

        try {
            return Optional.of(q.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Club> findAllClubByVille(Ville pVille) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Club c WHERE c.ville=:ville", Club.class)
                    .setParameter("ville", pVille)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
