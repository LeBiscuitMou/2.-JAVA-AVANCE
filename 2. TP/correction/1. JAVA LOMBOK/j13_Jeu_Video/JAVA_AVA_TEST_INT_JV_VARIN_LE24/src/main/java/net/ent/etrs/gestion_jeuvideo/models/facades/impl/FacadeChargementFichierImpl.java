package net.ent.etrs.gestion_jeuvideo.models.facades.impl;

import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoConsole;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoFabriquant;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoJeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.dao.impl.DaoFactory;
import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.EntitiesFactory;
import net.ent.etrs.gestion_jeuvideo.models.entities.Fabriquant;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.JeuVideoException;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeChargementFichier;
import net.ent.etrs.gestion_jeuvideo.models.references.ConstMetier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class FacadeChargementFichierImpl implements IFacadeChargementFichier {
    IDaoConsole daoConsole = DaoFactory.getDaoConsole();
    IDaoFabriquant daoFabriquant = DaoFactory.getDaoFabriquant();
    IDaoJeuVideo daoJeuVideo = DaoFactory.getDaoJeuVideo();
    protected FacadeChargementFichierImpl() {

    }

    @Override
    public void initialisation() throws BusinessException {
        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/inti_gestion_jeuxvideo.csv").toURI()));

            for (String line : Files.readAllLines(fichierInit)) {
                String[] data = line.split(";");
                if (data[0].equals("Console")){
                    initialisationPourConsole(data);
                }

                if (data[0].equals("Jeu")){
                    initialisationPourJeu(data);
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new BusinessException(e);
        }

    }

    /**
     * Partie de l'initialisation pour les jeux vidéo.
     *
     * @param data les données relatives aux jeux vidéos
     * @throws BusinessException l'exception lancée
     */
    private void initialisationPourJeu(String[] data) throws BusinessException {
        //Jeu;Overwatch;24/05/2016;Blizzard Entertainment;01/01/1991;USA;fps;Playstation 4;Xbox One;Switch
        String nomJeu = data[1].trim();
        LocalDate dateSortie = LocalDate.parse(data[2].trim(), ConstMetier.DATE_FORMAT);
        String nomFabriquant = data[3].trim();
        LocalDate dateCreationFabriquant = LocalDate.parse(data[4].trim(), ConstMetier.DATE_FORMAT);
        Pays paysFabriquant = Pays.valueOf(data[5].trim().toUpperCase());
        Genre genre = Genre.valueOf(data[6].trim().toUpperCase());

        Fabriquant fabriquant = createAndSaveFabriquant(nomFabriquant, dateCreationFabriquant, paysFabriquant);

        try {
            // je crée le jeu vidéo
            JeuVideo jeu = EntitiesFactory.fabriquerJeuVideo(nomJeu, dateSortie, genre, fabriquant);
            for (int i = 7; i < data.length; i++) {
                // j'ajoute les consoles au jeu vidéo
                jeu.ajouterConsole(daoConsole.findConsoleByIdentity(data[i]).orElse(null));
            }
            // je sauvegarde le jeu vidéo
            daoJeuVideo.save(jeu);
        } catch (EntitiesFactoryException | DaoException | JeuVideoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Partie de l'initialisation pour les consoles.
     *
     * @param data les données relatives aux consoles
     * @throws BusinessException l'exception lancée
     */
    private void initialisationPourConsole(String[] data) throws BusinessException {
        //Console;Playstation 4;Sony Interactive Entertainment;16/11/1993;USA;15/11/2013;USA;29/11/2013;France;22/02/2014;Japon
        String nomConsole = data[1].trim();
        String nomFabriquant = data[2].trim();
        LocalDate dateCreationFab = LocalDate.parse(data[3].trim(), ConstMetier.DATE_FORMAT);
        Pays paysFabriquant = Pays.valueOf(data[4].trim().toUpperCase());

        try {
            // Récupère ou crée le fabricant s'il n'existe pas
            Fabriquant fabriquant = createAndSaveFabriquant(nomFabriquant, dateCreationFab, paysFabriquant);

            // Initialise la map des sorties
            Map<Pays, LocalDate> mapSorties = new HashMap<>();
            for (int i = 5; i < data.length; i+=2) {
                mapSorties.put(Pays.valueOf(data[i+1].toUpperCase()), LocalDate.parse(data[i], ConstMetier.DATE_FORMAT));
            }

            Console console = EntitiesFactory.fabriquerConsole(nomConsole, fabriquant, mapSorties);
            daoConsole.save(console);
        } catch (BusinessException | EntitiesFactoryException | DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Récupère-le fabriquant en BDD s'il existe ou le crée le cas échéant.
     *
     * @param nomFabriquant le nom du fabricant
     * @param dateCreationFabriquant la date de création du fabricant
     * @param paysFabriquant le pays du fabricant
     * @return le fabricant trouvé/crée
     * @throws BusinessException l'exception lancée
     */
    private Fabriquant createAndSaveFabriquant(String nomFabriquant, LocalDate dateCreationFabriquant, Pays paysFabriquant) throws BusinessException {
        try {
            // récupère l'instance stockée en BDD si elle existe déjà, ou la crée si elle n'existe pas.
            Fabriquant fabriquant = daoFabriquant.findFabriquantByIdentity(nomFabriquant)
                    .orElse(EntitiesFactory.fabriquerFabriquant(nomFabriquant, dateCreationFabriquant, paysFabriquant));

            return daoFabriquant.save(fabriquant);
        } catch (EntitiesFactoryException | DaoException e) {
            throw new BusinessException(e);
        }
    }
}
