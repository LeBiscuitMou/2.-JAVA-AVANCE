package net.ent.etrs.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Personne fabriquerPersonne(String nom, String prenom, LocalDate dateNaissance){
        Personne p = new Personne();
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setDateNaissance(dateNaissance);
        validerObjetGenerique(p);

        return p;
    }

    private static <T> void validerObjetGenerique(T p) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> s = validator.validate(p);
        if(!s.isEmpty()){
            String ret = "";
            for (ConstraintViolation<T> c : s){
                ret += c.getMessage()+"\n";
            }

           throw new ValidationException(ret);
        }
    }

}
