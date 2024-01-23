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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Personne>> s = validator.validate(p);
        if(!s.isEmpty()){
        String ret = "";
            for (ConstraintViolation<Personne> c : s){
                ret += c.getMessage()+"\n";
            }

           throw new ValidationException(ret);
        }

        return p;
    }

}
