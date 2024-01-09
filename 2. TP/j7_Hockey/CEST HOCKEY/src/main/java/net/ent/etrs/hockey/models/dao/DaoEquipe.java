package net.ent.etrs.hockey.models.dao;

import net.ent.etrs.hockey.models.dao.base.BaseDao;
import net.ent.etrs.hockey.models.entities.Equipe;
import net.ent.etrs.hockey.models.entities.Joueur;
import net.ent.etrs.hockey.models.facades.exception.BusinessException;

import java.util.Map;
import java.util.Set;

public interface DaoEquipe extends BaseDao<Equipe> {
    Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat);

    Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat);

    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat);

    Equipe findEquipeByName(String name) throws BusinessException;
}
