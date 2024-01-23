package net.ent.etrs.championnathockey.models.daos.impl;

import net.ent.etrs.championnathockey.models.daos.DaoJoueur;
import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Joueur;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DaoJoueurImpl extends AbstractJpaDao<Joueur, Serializable> implements DaoJoueur {

    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws DaoException {
        try {
            TypedQuery<Joueur> tp = this.em.createQuery(
                    "SELECT t FROM Joueur t " +
                            "WHERE t.dateNaissance < :date ", Joueur.class);
            tp.setParameter("date", date);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public Optional<Joueur> findJoueurBestScoreur() throws DaoException {
        try {
            TypedQuery<Joueur> tp = this.em.createQuery(
                    "SELECT t FROM Joueur t " +
                            "WHERE t.nbPoint = (" +
                            "SELECT MAX(j1.nbPoint) FROM Joueur j1)", Joueur.class);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
