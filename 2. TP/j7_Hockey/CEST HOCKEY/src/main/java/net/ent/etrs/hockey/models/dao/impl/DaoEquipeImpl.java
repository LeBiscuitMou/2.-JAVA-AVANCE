package net.ent.etrs.hockey.models.dao.impl;

import net.ent.etrs.hockey.models.dao.DaoEquipe;
import net.ent.etrs.hockey.models.dao.base.JpaBaseDao;
import net.ent.etrs.hockey.models.entities.Equipe;
import net.ent.etrs.hockey.models.entities.Joueur;
import net.ent.etrs.hockey.models.facades.exception.BusinessException;

import java.util.Map;
import java.util.Set;

public class DaoEquipeImpl extends JpaBaseDao<Equipe> implements DaoEquipe {
    @Override
    public Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) {
        return null;
    }

    @Override
    public Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) {
        return null;
    }

    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) {
        return null;
    }

    @Override
    public Equipe findEquipeByName(String name) throws BusinessException {
        return null;
    }
}