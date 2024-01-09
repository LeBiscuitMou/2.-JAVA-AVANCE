package net.ent.etrs.championnathockey.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.championnathockey.commons.validator.ValidException;
import net.ent.etrs.championnathockey.commons.validator.ValidatorUtils;
import net.ent.etrs.championnathockey.models.entities.references.Poste;
import net.ent.etrs.championnathockey.models.entities.references.Ville;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Equipe fabriquerEquipe(String nom, Ville ville) throws ValidException {
        Equipe equipe = new Equipe();
        equipe.setNom(nom);
        equipe.setVille(ville);
        return equipe;
    }

    public static Joueur fabriquerJoueur(final String nom, final String prenom, final LocalDate dateNaissance, final Poste poste) throws ValidException {
        Joueur joueur = new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setDateNaissance(dateNaissance);
        joueur.setPoste(poste);
        return joueur;
    }

    public static Championnat fabriquerChampionnat(String nom, Integer annee) throws ValidException {
        Championnat champ = new Championnat();
        champ.setNomChampionnat(nom);
        champ.setAnneeChampionnat(annee);
        return champ;
    }

    public static Match fabriquerMatch(){
        return new Match();
    }

}
