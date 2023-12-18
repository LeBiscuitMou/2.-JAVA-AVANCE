package net.ent.etrs.projet.models.entities;

import net.ent.etrs.projet.models.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.entities.references.ConstantesMetier;

import java.time.LocalDate;
import java.util.List;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    public static Auteur fabriquerAuteur(String nom, String prenom) throws EntitiesFactoryException {
        try {
            return new Auteur(nom, prenom);
        } catch (AuteurException e) {
            throw new EntitiesFactoryException(ConstantesMetier.CONSTRUCTION_AUTEUR_ERROR, e);
        }
    }

    public static MaisonEdition fabriquerMaisonEdition(String nom, LocalDate dateCreation, Pays pays) throws EntitiesFactoryException {
        try {
            return new MaisonEdition(nom, dateCreation, pays);
        } catch (MaisonEditionException e) {
            throw new EntitiesFactoryException(ConstantesMetier.CONSTRUCTION_MAISON_ERROR, e);
        }
    }

    public static JeuSociete fabriquerJeuSociete(String nom, int joueurMin, int joueurMax, float prixHT, Auteur auteur, MaisonEdition maisonEdition, List<Mecanisme> lesMecanismes) throws EntitiesFactoryException {
        try {
            return new JeuSociete(nom, joueurMin, joueurMax, prixHT, maisonEdition, auteur, lesMecanismes);
        } catch (JeuSocieteException e) {
            throw new EntitiesFactoryException(ConstantesMetier.CONSTRUCTION_JDS_ERROR, e);
        }
    }
}
