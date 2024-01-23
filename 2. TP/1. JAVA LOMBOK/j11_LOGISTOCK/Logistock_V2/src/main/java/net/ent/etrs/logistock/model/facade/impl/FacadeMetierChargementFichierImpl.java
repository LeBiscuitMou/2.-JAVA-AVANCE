package net.ent.etrs.logistock.model.facade.impl;

import net.ent.etrs.logistock.model.dao.IDaoArticle;
import net.ent.etrs.logistock.model.dao.IDaoClient;
import net.ent.etrs.logistock.model.dao.IDaoCommande;
import net.ent.etrs.logistock.model.dao.impl.DaoFactory;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.EntitiesFactory;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import net.ent.etrs.logistock.model.facade.IFacadeMetierChargementFichier;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeMetierChargementFichierImpl implements IFacadeMetierChargementFichier {
    private IDaoArticle daoArticle;
    private IDaoClient daoClient;
    private IDaoCommande daoCommande;

    public FacadeMetierChargementFichierImpl() {
        this.daoArticle = DaoFactory.getDaoArticle();
        this.daoClient = DaoFactory.getDaoClient();
        this.daoCommande = DaoFactory.getDaoCommande();
    }

    @Override
    public void initialisation() throws Exception {
        initArticle();
        initClient();
    }

    private void initArticle() throws Exception {
        Path fichierInit = Paths.get(Objects.requireNonNull(FacadeMetierChargementFichierImpl.class.getResource("/articles.csv").toURI()));
        for(String line : Files.readAllLines(fichierInit)){
            String[] ligne = line.split("\\|");
            String designation = ligne[0];
            BigDecimal prix = BigDecimal.valueOf(Long.parseLong(ligne[1]));
            Emplacement emplacement = Emplacement.valueOf(ligne[2].toUpperCase());

            Article article = EntitiesFactory.fabriquerArticle(designation, prix, emplacement);

            daoArticle.save(article);
        }
    }

    private void initClient() {
    }
}