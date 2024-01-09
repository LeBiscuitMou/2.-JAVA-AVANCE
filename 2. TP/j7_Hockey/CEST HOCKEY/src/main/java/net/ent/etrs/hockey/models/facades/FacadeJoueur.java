package net.ent.etrs.hockey.models.facades;

import net.ent.etrs.hockey.models.entities.Joueur;

import java.time.LocalDate;
import java.util.Set;

public interface FacadeJoueur {
    void saveJoueur(Joueur joueur);

    void deleteJoueur(Long idJoueur);

    Set<Joueur> findAllJoueurs();

    Set<Joueur> findAllJoueurBirthBefore(LocalDate date);

    Joueur findJoueurBestScoreur();
}
