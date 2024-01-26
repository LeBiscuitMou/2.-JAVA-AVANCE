package net.ent.etrs.projetjavamaier.models.facade.impl;


import net.ent.etrs.projetjavamaier.models.daos.ConsoleDao;
import net.ent.etrs.projetjavamaier.models.daos.DaoFactory;
import net.ent.etrs.projetjavamaier.models.daos.FabriquantDao;
import net.ent.etrs.projetjavamaier.models.daos.JeuVideoDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.Console;
import net.ent.etrs.projetjavamaier.models.entities.EntitiesFactory;
import net.ent.etrs.projetjavamaier.models.entities.Fabriquant;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.projetjavamaier.models.entities.exceptions.JeuVideoException;
import net.ent.etrs.projetjavamaier.models.entities.references.ConstantesMetier;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;
import net.ent.etrs.projetjavamaier.models.facade.FacadeChargementFichier;

import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class FacadeChargementFichierImpl implements FacadeChargementFichier {

    //declarer les dao de toutes les entités que l'on va initialiser
    ConsoleDao daoConsole = DaoFactory.getConsoleDao();
    JeuVideoDao daoJeuVideo = DaoFactory.getJeuVideoDao();
    FabriquantDao daoFabriquant = DaoFactory.getFabriquantDao();

    /**
     * Méthode permettant d'initialiser les entitées.
     *
     * @Path fichierSourceCsv -> Lien du fichier source d'initialisation
     *
     * @Enum -> NomEnum.valueOf(decoupage[numéroligne])
     * @Date -> parseDate(decoupage[numéroLigne],ConstantesMetier.formatDate)
     * ATTENTION : PLUSIEURS format de date peuvent être dans une init
     *
     * @throws BusinessException
     * @throws IOException
     */
    @Override
    public void initialisation() throws BusinessException {
        try {


            //SI FICHIER INIT A METTRE DANS RESSOURCES
             Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/inti_gestion_jeuxvideo.csv").toURI()));


           Fabriquant fabriquant = null;
            String initialisation;


            for (int i = 0; i < Files.readAllLines(fichierInit).size(); i++) {
                initialisation = Files.readAllLines(fichierInit).get(i);
                String[] decoupage = initialisation.split(";");
                if (decoupage.length > 0) {

                        //La ligne commence par CONSOLE
                    //Console;Playstation 5;Sony Interactive Entertainment;16/11/1993;USA;12/11/2020;Japon;12/11/2020;USA;19/11/2020;France
                        if (decoupage[0].contains("CONSOLE")) {

                            String nom = decoupage[1];
                            String nomFabriquant = decoupage[2];
                            LocalDate dateCreation = LocalDate.parse(decoupage[3], ConstantesMetier.formatDate);
                            Pays pays = Pays.valueOf(decoupage[4]);
                            Map<Pays,LocalDate> sorties = new HashMap<>() ;
                            for (int j = 4; j < decoupage.length; j+=2) {
                                sorties.put(Pays.valueOf(decoupage[j]), LocalDate.parse(decoupage[5], ConstantesMetier.formatDate));
                            }
                            Fabriquant fabriquant1 = EntitiesFactory.fabriquerFabriquant(nomFabriquant, dateCreation, pays);
                            Console console = EntitiesFactory.fabriquerConsole(nom,fabriquant1,sorties);
                            daoConsole.save(console);
                            daoFabriquant.save(fabriquant1);
                        }


                    //La ligne commence par JEU
                    //Jeu;Fallout 4;10/11/2015;BETHESDA GAME STUDIOS;01/01/2001;USA;fps;Playstation 4;Xbox One
                    if (decoupage[0].contains("JEU")) {
                        String nom = decoupage[1];
                        LocalDate dateSortie = LocalDate.parse(decoupage[2], ConstantesMetier.formatDate);
                        String nomFabriquant = decoupage[3];
                        LocalDate dateCreation = LocalDate.parse(decoupage[4], ConstantesMetier.formatDate);
                        Pays pays = Pays.valueOf(decoupage[5]);
                        Genre genre = Genre.valueOf(decoupage[6]);
                        Set<Console> conso = new HashSet<>();
                        for (int j = 6; j < decoupage.length; j++){
                            conso.add(daoConsole.findByName(decoupage[j]));
                        }

                        Fabriquant fabriquant1 = EntitiesFactory.fabriquerFabriquant(nomFabriquant, dateCreation, pays);
                        JeuVideo jv = EntitiesFactory.fabriquerJeuVideo(nom, genre,dateSortie,fabriquant1 );




                        daoFabriquant.save(fabriquant1);
                        daoJeuVideo.save(jv);


                    }


                    }

                }
            } catch (EntitiesFactoryException | IOException ex) {

        } catch (URISyntaxException | DaoException e) {
            throw new RuntimeException(e);
        }
    }
    }