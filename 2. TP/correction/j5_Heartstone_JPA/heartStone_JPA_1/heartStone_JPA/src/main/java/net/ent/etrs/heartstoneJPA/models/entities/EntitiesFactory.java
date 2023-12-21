package net.ent.etrs.heartstoneJPA.models.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.heartstoneJPA.models.commons.validator.ValidException;
import net.ent.etrs.heartstoneJPA.models.commons.validator.ValidatorUtils;
import net.ent.etrs.heartstoneJPA.models.entities.references.Classe;
import net.ent.etrs.heartstoneJPA.models.entities.references.TypeCarte;
import net.ent.etrs.heartstoneJPA.models.exceptions.CarteException;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Carte fabriquerCarte(String nom, Integer cout, LocalDate dateSortie, TypeCarte tc, Classe classe, Set<Caracteristique> caracteristiques) throws ValidException {
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
        return ValidatorUtils.validate(carte);
    }
}
