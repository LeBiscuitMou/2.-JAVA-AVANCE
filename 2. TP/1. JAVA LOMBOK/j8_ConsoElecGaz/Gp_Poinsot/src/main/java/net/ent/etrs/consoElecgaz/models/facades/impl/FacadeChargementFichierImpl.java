package net.ent.etrs.consoElecgaz.models.facades.impl;

import net.ent.etrs.consoElecgaz.models.daos.DaoActivitePrincipale;
import net.ent.etrs.consoElecgaz.models.daos.DaoConsommation;
import net.ent.etrs.consoElecgaz.models.daos.DaoOperateur;
import net.ent.etrs.consoElecgaz.models.daos.DaoRegion;
import net.ent.etrs.consoElecgaz.models.entities.*;
import net.ent.etrs.consoElecgaz.models.entities.references.CodeConso;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;
import net.ent.etrs.consoElecgaz.models.entities.references.GrandSecteur;
import net.ent.etrs.consoElecgaz.models.facades.IFacadeChargementFichier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeChargementFichierImpl implements IFacadeChargementFichier {

    DaoConsommation daoConsommation;
    DaoOperateur daoOperateur;
    DaoRegion daoRegion;
    DaoActivitePrincipale daoActivitePrincipale;

    @Override
    public void initialisation() throws Exception {
        Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/conso-elec-gaz-annuelle-par-naf-agregee-region.csv").toURI()));
        for (String line : Files.readAllLines(fichierInit)) {
            String[] ligne = line.split(";");
            String operateur = ligne[0];
            Integer annee = Integer.parseInt(ligne[1]);
            Filiere filiere = Filiere.valueOf(ligne[2]);
            CodeConso codeConso = CodeConso.valueOf(ligne[3]);
            // TODO Libelle conso
            GrandSecteur grandSecteur = GrandSecteur.valueOf(ligne[5]);
            // TODO Libelle grand secteur
            Integer codNAF = Integer.parseInt(ligne[7]);
            String libelleNAF = ligne[8];
            Float conso = Float.parseFloat(ligne[9]);
            Integer pdl = Integer.parseInt(ligne[10]);
            Integer codeRegion = Integer.parseInt(ligne[11]);
            String libelleRegion = ligne[12];

            Region r = EntitiesFactory.fabriquerRegion(libelleRegion, codeRegion);

            Consomation c = EntitiesFactory.fabriquerConsomation();

            Operateur o = EntitiesFactory.fabriquerOperateur();

            ActivitePrincipale ap = EntitiesFactory.fabriquerActivitePrincipale();

            
        }
    }
}