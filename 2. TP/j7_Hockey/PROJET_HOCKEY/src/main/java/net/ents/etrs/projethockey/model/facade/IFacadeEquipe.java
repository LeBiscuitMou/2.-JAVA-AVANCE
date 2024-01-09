package net.ents.etrs.projethockey.model.facade;

import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;
import net.ents.etrs.projethockey.model.facade.exceptions.BusinessException;

import java.util.Map;
import java.util.Set;

public interface IFacadeEquipe {
    void saveEquipe(Equipe equipe);
    void deleteEquipe(Long idEquipe);
    Set<Equipe> findAllEquipes();
    Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat);

    Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat);
    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat);

    Equipe findEquipeByName(String name) throws BusinessException;
}
