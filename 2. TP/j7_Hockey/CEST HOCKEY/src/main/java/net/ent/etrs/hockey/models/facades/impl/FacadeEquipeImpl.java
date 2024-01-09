package net.ent.etrs.hockey.models.facades.impl;

import net.ent.etrs.hockey.models.entities.Equipe;
import net.ent.etrs.hockey.models.entities.Joueur;
import net.ent.etrs.hockey.models.facades.FacadeEquipe;
import net.ent.etrs.hockey.models.facades.exception.BusinessException;

import java.util.Map;
import java.util.Set;

public class FacadeEquipeImpl implements FacadeEquipe {
    @Override
    public void saveEquipe(Equipe equipe) {

    }

    @Override
    public void deleteEquipe(Long idEquipe) {

    }

    @Override
    public Set<Equipe> findAllEquipes() {
        return null;
    }

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