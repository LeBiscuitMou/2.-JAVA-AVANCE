package net.ents.etrs.projethockey.model.facade;

import net.ents.etrs.projethockey.model.dao.IDaoJoueur;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface IFacadeJoueur {


    void saveJoueur(Joueur joueur);
    void deleteJoueur(Long idJoueur);
    Set<Joueur> findAllJoueurs();
    Set<Joueur> findAllJoueurBirthBefore(LocalDate date);
    Joueur findJoueurBestScoreur();
    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat);
}
