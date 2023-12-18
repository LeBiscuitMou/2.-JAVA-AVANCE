package net.ent.etrs.cartes.models.facade;

import net.ent.etrs.cartes.models.entities.Caracteristique;
import net.ent.etrs.cartes.models.entities.Carte;
import net.ent.etrs.cartes.models.entities.references.Classe;
import net.ent.etrs.cartes.models.entities.references.TypeCarte;
import net.ent.etrs.cartes.models.facade.exception.BusinessException;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface FacadeMetier {
    void initialisation(List<String> stringList);

    Carte creerCarte(Carte carte) throws BusinessException;

    Carte selectionnerCarte(String nom);

    void modifierCarte(Carte carte) throws BusinessException;

    void supprimerCarte(Carte carte);

    Caracteristique creerCaracteristique(Caracteristique caracteristique) throws BusinessException;

    Caracteristique selectionnerCaracteristique(String code);

    void modifierCaracteristique(Caracteristique caracteristique) throws BusinessException;

    void supprimerCaracteristique(Caracteristique caracteristique) throws BusinessException;

    List<Carte> recupererCartes(TypeCarte typeCarte);

    List<Carte> recupererCartes(Classe classe);

    Map<Month, List<Carte>> recupererCartesAvecMois();
}