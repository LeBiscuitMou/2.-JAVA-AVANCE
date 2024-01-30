package net.ent.etrs.gestion_jeuvideo.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.gestion_jeuvideo.models.commons.ValidatorUtils;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.ConsoleException;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.gestion_jeuvideo.models.references.ConstMetier;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Console fabriquerConsole(String nom, Fabriquant fabriquant, Map<Pays, LocalDate> sorties) throws EntitiesFactoryException {
        String errorMsg = "";

        Console console = new Console();
        console.setNom(nom);
        console.setFabriquant(fabriquant);
        try {
            console.setSortiesPays(sorties);
        } catch (ConsoleException e) {
            errorMsg = e.getMessage();
        }

        try {
            ValidatorUtils.refactorException(console);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage() + '\n' + errorMsg);
        }

        if(!errorMsg.isEmpty()){
            throw new EntitiesFactoryException(ConstMetier.ERROR_CREATION + "Console" + '\n' + errorMsg);
        }

        return console;
    }
    
    public static Console fabriquerConsole(String nom, Fabriquant fabriquant) throws EntitiesFactoryException {
        return fabriquerConsole(nom, fabriquant, null);
    }

    public static JeuVideo fabriquerJeuVideo(String nom, LocalDate dateSortie, Genre genre, Fabriquant studioDev) throws EntitiesFactoryException {
        JeuVideo jeuVideo = new JeuVideo();
        jeuVideo.setNom(nom);
        jeuVideo.setDateSortie(dateSortie);
        jeuVideo.setStudioDev(studioDev);
        jeuVideo.setGenre(genre);
    
        try {
            ValidatorUtils.refactorException(jeuVideo);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
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
            throw new EntitiesFactoryException(e.getMessage());
        }
    
        return fabriquant;
    }

    

}



