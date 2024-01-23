package net.ent.etrs.hockey.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.hockey.model.commons.validator.ValidException;
import net.ent.etrs.hockey.model.commons.validator.ValidatorUtils;
import net.ent.etrs.hockey.model.entities.references.Poste;
import net.ent.etrs.hockey.model.entities.references.Ville;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Championnat fabriquerChampionnat(String nom, Integer annee) throws ValidException {
        Championnat championnat = new Championnat();
        championnat.setNomChampionnat(nom);
        championnat.setAnneeChampionnat(annee);
        return ValidatorUtils.validate(championnat);
    }

    public static Joueur fabriquerJoueur(String nom, String prenom, LocalDate dateNaissance, Poste poste) throws ValidException {
        Joueur joueur = new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setDateNaissance(dateNaissance);
        joueur.setPoste(poste);
        return ValidatorUtils.validate(joueur);
    }

    public static Equipe fabriquerEquipe(String nom, Ville ville) throws ValidException {
        Equipe equipe = new Equipe();
        equipe.setNom(nom);
        equipe.setVille(ville);
        return ValidatorUtils.validate(equipe);
    }

    public static Match fabriquerMatch() throws ValidException {
        return ValidatorUtils.validate(new Match());
    }
}
