package net.ents.etrs.projethockey.model.facade;


import net.ents.etrs.projethockey.model.entities.Championnat;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;
import net.ents.etrs.projethockey.model.entities.Match;
import net.ents.etrs.projethockey.model.facade.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface IFacade {

    void saveJoueur(Joueur joueur);

    void saveChampionnat(Championnat championnat);

    void saveEquipe(Equipe equipe);

    void saveMatch(Match match);

    void deleteJoueur(Long idJoueur);

    void deleteChampionnat(Long idChampionnat);

    void deleteEquipe(Long idEquipe);

    void deleteMatch(Long idMatch);

    Set<Championnat> findAllChampionnats();

    Set<Equipe> findAllEquipes();

    Set<Joueur> findAllJoueurs();

    Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat);

    Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat);

    Set<Joueur> findAllJoueurBirthBefore(LocalDate date);

    Joueur findJoueurBestScoreur();

    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat);

    Equipe findEquipeByName(String name) throws BusinessException;

}
