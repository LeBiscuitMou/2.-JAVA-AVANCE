package net.ent.etrs.projet.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.commons.ValidatorUtils;
import net.ent.etrs.projet.models.entities.references.Type;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.exceptions.SoigneurException;
import net.ent.etrs.projet.models.references.ConstMetier;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Animal fabriquerAnimal(String nom, LocalDate dateDeNaissance, double poids, double taille, boolean dangereux, Type type) throws EntitiesFactoryException {
        Animal animal = new Animal();
        animal.setNom(nom);
        animal.setDateDeNaissance(dateDeNaissance);
        animal.setPoids(poids);
        animal.setTaille(taille);
        animal.setDangereux(dangereux);
        animal.setType(type);

        try {
            ValidatorUtils.refactorException(animal);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return animal;
    }
    
    public static Soigneur fabriquerSoigneur(String nom, String prenom, LocalDate dateDeNaissance, LocalDate dateArrivee, List<Type> typeList) throws EntitiesFactoryException {
        StringBuilder errorMsg = new StringBuilder();

        Soigneur soigneur = new Soigneur();
        soigneur.setNom(nom);
        soigneur.setPrenom(prenom);
        soigneur.setDateDeNaissance(dateDeNaissance);
        soigneur.setDateArrivee(dateArrivee);

//        for(Type t : typeList){
//            try {
//                soigneur.ajouterType(t);
//            } catch (SoigneurException e) {
//                errorMsg.append(e.getMessage()).append('\n');
//            }
//        }
    
        try {
            ValidatorUtils.refactorException(soigneur);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage() + '\n' + errorMsg);
        }

        if(!errorMsg.isEmpty()){
            throw new EntitiesFactoryException(ConstMetier.ERROR_CREATION + "Soigneur" + '\n' + errorMsg);
        }
    
        return soigneur;
    }
    

}



