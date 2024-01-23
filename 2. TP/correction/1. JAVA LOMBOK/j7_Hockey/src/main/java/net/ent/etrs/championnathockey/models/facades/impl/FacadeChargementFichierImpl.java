package net.ent.etrs.championnathockey.models.facades.impl;

import net.ent.etrs.championnathockey.models.daos.DaoEquipe;
import net.ent.etrs.championnathockey.models.daos.DaoFactory;
import net.ent.etrs.championnathockey.models.daos.DaoJoueur;
import net.ent.etrs.championnathockey.models.entities.EntitiesFactory;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.entities.references.ConstanteMetier;
import net.ent.etrs.championnathockey.models.entities.references.Poste;
import net.ent.etrs.championnathockey.models.entities.references.Ville;
import net.ent.etrs.championnathockey.models.facades.FacadeChargementFichier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class FacadeChargementFichierImpl implements FacadeChargementFichier {

    DaoEquipe daoEquipe;
    DaoJoueur daoJoueur;

    public FacadeChargementFichierImpl() {
        this.daoEquipe = DaoFactory.getDaoEquipe();
        this.daoJoueur = DaoFactory.getDaoJoueur();
    }


    @Override
    public void initialisation() throws Exception {
        Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/initJoueurHockey.csv").toURI()));
        for (String line : Files.readAllLines(fichierInit)) {
            String[] ligne = line.split(";");
            String nom = ligne[0];
            String prenom = ligne[1];
            LocalDate dateNaissance = LocalDate.parse(ligne[2], ConstanteMetier.formatDate);
            Poste poste = Poste.valueOf(ligne[3]);
            String nomEquipe = ligne[4];
            Ville nomVille = Ville.valueOf(ligne[5]);

            Joueur j = EntitiesFactory.fabriquerJoueur(nom, prenom, dateNaissance, poste);

            Optional<Equipe> oEquipe = daoEquipe.findByName(nomEquipe);
            Equipe e;
            if (oEquipe.isPresent()) {
                e = oEquipe.get();
            } else {
                e = EntitiesFactory.fabriquerEquipe(nomEquipe, nomVille);
            }
            j = daoJoueur.save(j);
            e.ajouterJoueur(j);
            daoEquipe.save(e);

        }
    }
}
