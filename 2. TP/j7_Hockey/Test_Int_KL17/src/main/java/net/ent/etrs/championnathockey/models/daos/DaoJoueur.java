package net.ent.etrs.championnathockey.models.daos;

import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Joueur;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface DaoJoueur extends BaseDao<Joueur, Serializable> {
    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws DaoException;

    public Optional<Joueur> findJoueurBestScoreur() throws DaoException;
}
