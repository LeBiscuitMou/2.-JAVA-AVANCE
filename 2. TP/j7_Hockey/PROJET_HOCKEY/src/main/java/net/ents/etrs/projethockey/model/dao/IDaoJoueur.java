package net.ents.etrs.projethockey.model.dao;

import net.ents.etrs.projethockey.model.dao.base.BaseDao;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface IDaoJoueur extends BaseDao<Joueur> {
    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) throws DaoException;
    Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws DaoException;
    Joueur findJoueurBestScoreur() throws DaoException;

}
