package net.ent.etrs.hockey.models.facades.impl;

import net.ent.etrs.hockey.models.entities.Joueur;
import net.ent.etrs.hockey.models.facades.FacadeJoueur;

import java.time.LocalDate;
import java.util.Set;

public class FacadeJoueurImpl implements FacadeJoueur {
    @Override
    public void saveJoueur(Joueur joueur) {

    }

    @Override
    public void deleteJoueur(Long idJoueur) {

    }

    @Override
    public Set<Joueur> findAllJoueurs() {
        return null;
    }

    @Override
    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) {
        return null;
    }

    @Override
    public Joueur findJoueurBestScoreur() {
        return null;
    }
}