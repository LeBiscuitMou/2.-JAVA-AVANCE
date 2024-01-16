package net.ent.etrs.hockey.model.dao;

import net.ent.etrs.hockey.model.dao.base.BaseDao;
import net.ent.etrs.hockey.model.entities.Equipe;
import net.ent.etrs.hockey.model.entities.Joueur;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface IDaoEquipe extends BaseDao<Equipe, Serializable> {
    Set<Equipe> findAllByYearAndName(Integer annee, String nomChampionnat);

    Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat);

    Equipe findEquipeByName(String name);

    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat);
}
