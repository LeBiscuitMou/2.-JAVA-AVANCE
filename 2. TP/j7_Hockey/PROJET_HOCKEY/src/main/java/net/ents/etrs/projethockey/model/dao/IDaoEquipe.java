package net.ents.etrs.projethockey.model.dao;

import net.ents.etrs.projethockey.model.dao.base.BaseDao;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;
import net.ents.etrs.projethockey.model.facade.exceptions.BusinessException;

import java.util.Map;
import java.util.Set;

public interface IDaoEquipe extends BaseDao<Equipe> {
   Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) throws DaoException;
    Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) throws DaoException;
    Equipe findEquipeByName(String name)  throws DaoException;
    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat)  throws DaoException;
}
