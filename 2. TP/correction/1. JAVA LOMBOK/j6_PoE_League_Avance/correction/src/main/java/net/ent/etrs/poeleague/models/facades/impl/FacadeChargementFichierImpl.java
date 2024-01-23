package net.ent.etrs.poeleague.models.facades.impl;


import net.ent.etrs.poeleague.models.dao.DaoChallenge;
import net.ent.etrs.poeleague.models.dao.DaoFactory;
import net.ent.etrs.poeleague.models.dao.DaoLeague;
import net.ent.etrs.poeleague.models.dao.DaoPersonnage;
import net.ent.etrs.poeleague.models.entities.Challenge;
import net.ent.etrs.poeleague.models.entities.EntitiesFactory;
import net.ent.etrs.poeleague.models.entities.League;
import net.ent.etrs.poeleague.models.entities.Personnage;
import net.ent.etrs.poeleague.models.entities.references.ConstantMetier;
import net.ent.etrs.poeleague.models.entities.references.LabySpecialite;
import net.ent.etrs.poeleague.models.facades.FacadeChargementFichier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

public class FacadeChargementFichierImpl implements FacadeChargementFichier {

    DaoLeague daoLeague;

    DaoChallenge daoChallenge;

    DaoPersonnage daoPersonnage;


    public FacadeChargementFichierImpl() {
        this.daoLeague = DaoFactory.getDaoLeague();
        this.daoChallenge = DaoFactory.getDaoChallenge();
        this.daoPersonnage = DaoFactory.getDaoPersonnage();

    }


    @Override
    public void initialisation() throws Exception {
        Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/InitPoeLeague.csv").toURI()));

        League league = null;
        Challenge challenge;
        Personnage personnage;

        for (String line : Files.readAllLines(fichierInit)) {
            String[] ligne = line.split(";");

            //La ligne commence par LEAGUE
            if (ligne[0].contains("LEAGUE")) {
                String nom = ligne[1];
                LocalDate dd = LocalDate.parse(ligne[2], ConstantMetier.DATE_FORMAT);
                LocalDate df = null;
                if (ligne.length > 3) {
                    df = LocalDate.parse(ligne[3], ConstantMetier.DATE_FORMAT);
                }

                league = EntitiesFactory.fabriquerLeague(nom, dd, df);
            }
            //La ligne commence par CHALLENGE
            if (ligne[0].contains("CHALLENGE")) {
                String nom = ligne[1];
                String desc = ligne[2];
                Integer reward = Integer.parseInt(ligne[3]);
                challenge = EntitiesFactory.fabriquerChallenge(nom, desc, reward);
                challenge = daoChallenge.save(challenge);
                if (Objects.nonNull(league)) {
                    league.ajouterChallenge(challenge);
                    daoLeague.save(league);
                }
            }
            //La ligne commence par PERSONNAGE
            if (ligne[0].contains("PERSONNAGE")) {
                String pseudo = ligne[2];
                LabySpecialite build = LabySpecialite.valueOf(ligne[3].toUpperCase());
                Integer level = Integer.parseInt(ligne[4]);
                Integer classement = Integer.parseInt(ligne[1]);
                personnage = EntitiesFactory.fabriquerPersonnage(pseudo, build, level);
                personnage = daoPersonnage.save(personnage);
                if (Objects.nonNull(league)) {
                    league.ajouterPersonnage(personnage, classement);
                    daoLeague.save(league);
                }

            }


        }
    }
}
