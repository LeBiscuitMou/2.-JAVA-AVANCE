package net.ent.etrs.jeuVideo.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.jeuVideo.model.commons.validator.ValidatorUtils;
import net.ent.etrs.jeuVideo.model.entities.exceptions.ConsoleException;
import net.ent.etrs.jeuVideo.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.jeuVideo.model.entities.references.ConstantesMetier;
import net.ent.etrs.jeuVideo.model.entities.references.Genre;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static JeuVideo fabriquerJeuVideo(String nom, LocalDate dateSortie, Genre genre, Fabriquant studioDev) throws EntitiesFactoryException {
        JeuVideo jeuVideo = new JeuVideo();
        jeuVideo.setNom(nom);
        jeuVideo.setDateSortie(dateSortie);
        jeuVideo.setGenre(genre);
        jeuVideo.setStudioDev(studioDev);

        try {
            ValidatorUtils.refactorException(jeuVideo);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(ConstantesMetier.ERROR_CREATION + JeuVideo.class.getSimpleName(), e);
        }

        return jeuVideo;
    }

    public static Fabriquant fabriquerFabriquant(String nom, LocalDate dateCreation, Pays pays) throws EntitiesFactoryException {
        Fabriquant fabriquant = new Fabriquant();
        fabriquant.setNom(nom);
        fabriquant.setDateCreation(dateCreation);
        fabriquant.setPays(pays);

        try {
            ValidatorUtils.refactorException(fabriquant);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(ConstantesMetier.ERROR_CREATION + Fabriquant.class.getSimpleName(), e);
        }

        return fabriquant;
    }

    public static Console fabriquerConsole(String nom, Fabriquant fabriquant, Map<Pays, LocalDate> sorties) throws EntitiesFactoryException {
        Console console = new Console();
        console.setNom(nom);
        console.setFabriquant(fabriquant);

        try {
            for(Map.Entry<Pays, LocalDate> sortiesEntry : sorties.entrySet()){
                console.setSortiesPays(sortiesEntry.getKey(), sortiesEntry.getValue());
            }
            ValidatorUtils.refactorException(console);
        }catch (ValidationException | ConsoleException e){
            throw new EntitiesFactoryException(ConstantesMetier.ERROR_CREATION + Console.class.getSimpleName(), e);
        }

        return console;
    }

    public static Console fabriquerConsole(String nom, Fabriquant fabriquant) throws EntitiesFactoryException {
        Console console = new Console();
        console.setNom(nom);
        console.setFabriquant(fabriquant);

        try {
            ValidatorUtils.refactorException(console);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return console;
    }
}
