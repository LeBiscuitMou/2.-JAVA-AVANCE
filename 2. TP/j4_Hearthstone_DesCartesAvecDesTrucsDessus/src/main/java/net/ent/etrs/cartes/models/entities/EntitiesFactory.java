package net.ent.etrs.cartes.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.cartes.models.entities.references.Classe;
import net.ent.etrs.cartes.models.entities.references.TypeCarte;

import javax.validation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Carte fabriquerCarte(String nom, Integer cout, LocalDate dateSortie, TypeCarte typeCarte, Classe classe) {
        Carte c = new Carte();
        c.setNom(nom);
        c.setCout(cout);
        c.setDateSortie(dateSortie);
        c.setTypeCarte(typeCarte);
        c.setClasse(classe);

        validerObjetGenerique(c);

        return c;
    }

    public static Caracteristique fabriquerCaracteristique(String code, String desc) {
        Caracteristique c = new Caracteristique();
        c.setCode(code);
        c.setDesc(desc);

        validerObjetGenerique(c);

        return c;
    }

    private static <T> void validerObjetGenerique(T c) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> s = validator.validate(c);
        if (!s.isEmpty()) {
            StringBuilder ret = new StringBuilder();
            for (ConstraintViolation<T> p : s) {
                ret.append(p.getMessage()).append("\n");
            }

            throw new ValidationException(ret.toString());
        }
    }
}