package net.ent.etrs.projetjavamaier.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projetjavamaier.commons.utils.ValidatorUtils;
import net.ent.etrs.projetjavamaier.models.entities.exceptions.ConsoleException;
import net.ent.etrs.projetjavamaier.models.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    //exemple
    public static Console fabriquerConsole(String nom, Fabriquant fabriquant, Map<Pays,LocalDate>sorties) throws EntitiesFactoryException {
        Console console = new Console();
        console.setNom(nom);
        console.setFabriquant(fabriquant);
        try {
            console.setSortiesPays(Pays.valueOf(sorties.keySet().iterator().next().toString()),LocalDate.now());
        } catch (ConsoleException e) {
            throw new RuntimeException(e);
        }

        try {
            ValidatorUtils.refactorException(console);
        }catch (ValidationException e){
            throw new EntitiesFactoryException("une érreur est survenue lors de la création de la console " ,e);
        }

        return console;
    }

    public static Fabriquant fabriquerFabriquant(String nom, LocalDate dateCreation, Pays pays) throws EntitiesFactoryException {
        Fabriquant fabriquant1 = new Fabriquant();
        fabriquant1.setNom(nom);
        fabriquant1.setPays(pays);
        fabriquant1.setDateCreation(dateCreation);

        try {
            ValidatorUtils.refactorException(fabriquant1);
        }catch (ValidationException e){
            throw new EntitiesFactoryException("une érreur est survenue lors de la création du fabriquant " ,e);
        }

        return fabriquant1;
    }

    public static JeuVideo fabriquerJeuVideo(String nom, Genre genre, LocalDate dateSortie, Fabriquant studioDev) throws EntitiesFactoryException {
        JeuVideo jeuVideo = new JeuVideo();
        jeuVideo.setNom(nom);
        jeuVideo.setDateSortie(dateSortie);
        jeuVideo.setGenre(genre);
        jeuVideo.setStudioDev(studioDev);



        try {
            ValidatorUtils.refactorException(jeuVideo);
        }catch (ValidationException e){
            throw new EntitiesFactoryException("une érreur est survenue lors de la création du jeuVideo " ,e);
        }

        return jeuVideo;
    }



}
