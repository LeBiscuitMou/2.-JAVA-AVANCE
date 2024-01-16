package net.ent.etrs.hockey.model.dao;

import net.ent.etrs.hockey.model.dao.base.BaseDao;
import net.ent.etrs.hockey.model.entities.Equipe;
import net.ent.etrs.hockey.model.entities.Joueur;
import net.ent.etrs.hockey.model.exceptions.DaoException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface IDaoJoueur extends BaseDao<Joueur, Serializable> {
    Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws DaoException;

    Joueur findJoueurBestScoreur() throws DaoException;
}
