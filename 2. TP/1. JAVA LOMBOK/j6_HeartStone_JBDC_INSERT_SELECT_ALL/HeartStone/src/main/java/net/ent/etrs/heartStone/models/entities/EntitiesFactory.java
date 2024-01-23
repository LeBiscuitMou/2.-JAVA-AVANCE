package net.ent.etrs.heartStone.models.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.heartStone.models.exceptions.CarteException;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Carte fabriquerCarte(String nom, Integer cout, LocalDate dateSortie, TypeCarte tc, Classe classe, Set<Caracteristique> caracteristiques) {
        Carte carte = new Carte();
        carte.setNom(nom);
        carte.setCout(cout);
        carte.setDateSortie(dateSortie);
        carte.setTypeCarte(tc);
        carte.setClasse(classe);
        if(Objects.nonNull(caracteristiques) && !caracteristiques.isEmpty()){
            for(Caracteristique c : caracteristiques) {
                try {
                    carte.ajouterCaracteristique(c);
                } catch (CarteException e) {
                    throw new ValidationException(e);
                }
            }
        }


        EntitiesValidation.validate(carte);

        return carte;
    }
}
