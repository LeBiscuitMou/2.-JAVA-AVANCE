package net.ent.etrs.hockey.model.dao.impl;

import net.ent.etrs.hockey.model.dao.IDaoJoueur;
import net.ent.etrs.hockey.model.dao.base.AbstractJpaDao;
import net.ent.etrs.hockey.model.entities.Joueur;
import net.ent.etrs.hockey.model.exceptions.DaoException;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class DaoJoueurImpl extends AbstractJpaDao<Joueur, Long> implements IDaoJoueur {
    @Override
    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws DaoException {
        try {
            TypedQuery<Joueur> tp = em.createQuery(
                    "SELECT j FROM Joueur j " +
                            "WHERE j.dateNaissance < :date", Joueur.class);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Joueur findJoueurBestScoreur() throws DaoException {
        try {
            TypedQuery<Joueur> tp = this.em.createQuery(
                    "SELECT j FROM Joueur j " +
                            "WHERE j.nbPoint = (" +
                            "SELECT MAX(j1.nbPoint) FROM Joueur j1)", Joueur.class);
            return tp.getSingleResult();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}