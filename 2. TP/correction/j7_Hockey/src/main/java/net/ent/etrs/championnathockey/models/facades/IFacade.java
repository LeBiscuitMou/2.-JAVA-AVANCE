package net.ent.etrs.championnathockey.models.facades;

import net.ent.etrs.championnathockey.models.entities.Championnat;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.entities.Match;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;

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
