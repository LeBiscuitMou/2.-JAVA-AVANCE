package net.ent.etrs.hockey.model.facade;

import net.ent.etrs.hockey.model.entities.Equipe;
import net.ent.etrs.hockey.model.entities.Joueur;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;

import java.util.Map;
import java.util.Set;

public interface IFacadeEquipe {
    void saveEquipe(Equipe equipe) throws BusinessException;
    void deleteEquipe(Long idEquipe) throws BusinessException;
    Set<Equipe> findAllEquipes() throws BusinessException;
    Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat);

    Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat);
    Equipe findEquipeByName(String name) throws BusinessException;

    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat);
}
