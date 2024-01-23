package net.ent.etrs.cycliste.model.entities;

import net.ent.etrs.cycliste.model.entities.references.TypeSpecialite;

import java.time.LocalDate;

public final class EntitiesFactory {
    
    private EntitiesFactory() {}
    
    public static Personne fabriquerPersonne(String nom, String prenom, LocalDate dateNaissance) throws Exception {
        return new Personne(nom, prenom, dateNaissance);
    }
    
    public static Equipe fabriquerEquipe(String nom, Personne entraineur) throws Exception {
        Equipe equipe = new Equipe(nom);
        equipe.setEntraineur(entraineur);
        return equipe;
    }
    
    public static Cycliste fabriquerCycliste(String nom, String prenom, LocalDate dateNaissance) throws Exception {
        return new Cycliste(nom, prenom, dateNaissance);
    }
    
    public static Cycliste fabriquerCycliste(String nom, String prenom, LocalDate dateNaissance, TypeSpecialite typeSpecialite) throws Exception {
        Cycliste cycliste = new Cycliste(nom, prenom, dateNaissance);
        cycliste.setSpecialite(typeSpecialite);
        return cycliste;
    }
    
    public static Epreuve fabriquerEpreuve(String nom,LocalDate dateDebut,LocalDate dateFin) throws Exception {
        return new Epreuve(nom, dateDebut, dateFin);
    }
    
    public static Epreuve fabriquerEpreuve(String nom,LocalDate dateDebut) throws Exception {
        return new Epreuve(nom, dateDebut, dateDebut);
    }
}
