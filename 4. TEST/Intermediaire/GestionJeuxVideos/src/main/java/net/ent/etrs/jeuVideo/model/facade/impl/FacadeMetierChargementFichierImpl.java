package net.ent.etrs.jeuVideo.model.facade.impl;

import net.ent.etrs.jeuVideo.model.entities.Console;
import net.ent.etrs.jeuVideo.model.entities.EntitiesFactory;
import net.ent.etrs.jeuVideo.model.entities.Fabriquant;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.jeuVideo.model.entities.exceptions.JeuVideoException;
import net.ent.etrs.jeuVideo.model.entities.references.ConstantesMetier;
import net.ent.etrs.jeuVideo.model.entities.references.Genre;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierConsole;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierFabriquant;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierJeuVideo;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class FacadeMetierChargementFichierImpl implements IFacadeMetierChargementFichier {
    private IFacadeMetierConsole facadeMetierConsole;
    private IFacadeMetierFabriquant facadeMetierFabriquant;
    private IFacadeMetierJeuVideo facadeMetierJeuVideo;

    public FacadeMetierChargementFichierImpl() {
        facadeMetierConsole = FacadeMetierFactory.fabriquerFacadeMetierConsole();
        facadeMetierFabriquant = FacadeMetierFactory.fabriquerFacadeMetierFabriquant();
        facadeMetierJeuVideo = FacadeMetierFactory.fabriquerFacadeMetierJeuVideo();
    }

    /**
     * Permet d'initialisaer les données du logiciel
     */
    @Override
    public void initialisation() throws BusinessException {
        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeMetierChargementFichierImpl.class.getResource("/inti_gestion_jeuxvideo.csv")).toURI());

            Fabriquant fabriquant;
            Console console;
            JeuVideo jeuxvideo = null;

            for (String line : Files.readAllLines(fichierInit)) {
                String[] data = line.split(";");
                if (data[0].startsWith("Console")) {
                    String nomConsole = data[1];

                    String nomFabriquant = data[2];
                    LocalDate dateCreationFabriquant = LocalDate.parse(data[3], ConstantesMetier.DATE_FORMAT);
                    Pays paysFabriquant = Pays.valueOf(data[4].toUpperCase());

                    Map<Pays, LocalDate> sortiesConsoles = new HashMap<>();
                    for (int i = 0; i < 3; i++) {
                        Pays paysConsole = Pays.valueOf(data[6].toUpperCase());
                        LocalDate dateSortieConsole = LocalDate.parse(data[5], ConstantesMetier.DATE_FORMAT);
                        sortiesConsoles.put(paysConsole, dateSortieConsole);
                    }

                    fabriquant = EntitiesFactory.fabriquerFabriquant(nomFabriquant, dateCreationFabriquant, paysFabriquant);
                    facadeMetierFabriquant.sauvegarderFabriquant(fabriquant);

                    console = EntitiesFactory.fabriquerConsole(nomConsole, fabriquant, sortiesConsoles);
                    facadeMetierConsole.sauvegarderConsole(console);
                } else {
                    String nomJeu = data[1];
                    LocalDate dateCreationJeu = LocalDate.parse(data[2], ConstantesMetier.DATE_FORMAT);

                    String nomFabriquant = data[3];
                    LocalDate dateCreationFabriquant = LocalDate.parse(data[4], ConstantesMetier.DATE_FORMAT);
                    Pays paysFabriquant = Pays.valueOf(data[5].toUpperCase());

                    Genre genre = Genre.valueOf(data[6].toUpperCase());

                    fabriquant = EntitiesFactory.fabriquerFabriquant(nomFabriquant, dateCreationFabriquant, paysFabriquant);
                    facadeMetierFabriquant.sauvegarderFabriquant(fabriquant);

                    jeuxvideo = EntitiesFactory.fabriquerJeuVideo(nomJeu, dateCreationJeu, genre, fabriquant);

                    Set<Console> lesConsoles = jeuxvideo.getPlateformes();

                    for (int i = 7; i < data.length; i++) {
                        for(Console c : lesConsoles){
                            if (c.getNom().equals(data[i])) {
                                jeuxvideo.addPlateform(c);
                            }
                        }
                    }

                    facadeMetierJeuVideo.sauvegarderJeuVideo(jeuxvideo);
                }
            }
        } catch (URISyntaxException | IOException | EntitiesFactoryException | JeuVideoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}