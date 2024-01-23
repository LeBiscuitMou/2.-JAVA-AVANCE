package net.ent.etrs.projet.models.entities;

import net.ent.etrs.projet.models.entities.exceptions.CyclisteException;
import net.ent.etrs.projet.models.entities.exceptions.ConstructionException;
import net.ent.etrs.projet.models.entities.exceptions.EquipeException;
import net.ent.etrs.projet.models.entities.exceptions.PersonneException;
import net.ent.etrs.projet.models.entities.references.ConstantesMetier;
import net.ent.etrs.projet.models.entities.references.TypeSpecialite;

import java.time.LocalDate;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    public static Personne fabriquerPersonne(String nom, String prenom, LocalDate dateNaissance) throws ConstructionException {
        try {
            return new Personne(nom, prenom, dateNaissance);
        } catch (PersonneException e) {
            throw new ConstructionException(ConstantesMetier.CONSTRUCTION_PERSONNE_ERREUR, e);
        }
    }

    public static Equipe fabriquerEquipe(String nom, Personne personne) throws ConstructionException {
        try {
            Equipe equipe =  new Equipe(nom);
            equipe.setEntraineur(personne);
            return equipe;
        } catch (EquipeException e) {
            throw new ConstructionException(ConstantesMetier.CONSTRUCTION_EQUIPE_ERREUR, e);
        }
    }

    public static Cycliste fabriquerCycliste(String nom, String prenom, LocalDate dateNaissance) throws ConstructionException {
        try {
            return new Cycliste(nom, prenom, dateNaissance);
        } catch (PersonneException e) {
            throw new ConstructionException(ConstantesMetier.CONSTRUCTION_CYCLISTE_ERREUR, e);
        }
    }

    public static Cycliste fabriquerCycliste(String nom, String prenom, LocalDate dateNaissance, TypeSpecialite specialite) throws ConstructionException {
        try {
            Cycliste c = new Cycliste(nom, prenom, dateNaissance);
            c.setSpecialite(specialite);
            return c;
        } catch (PersonneException | CyclisteException e) {
            throw new ConstructionException(ConstantesMetier.CONSTRUCTION_CYCLISTE_ERREUR, e);
        }
    }

    public static Epreuve fabriquerEpreuve(String nom,LocalDate dateDebut,LocalDate dateFin) {
        return new Epreuve(nom, dateDebut, dateFin);
    }

    public static Epreuve fabriquerEpreuve(String nom,LocalDate dateDebut) {
        return new Epreuve(nom, dateDebut, dateDebut);
    }
}
