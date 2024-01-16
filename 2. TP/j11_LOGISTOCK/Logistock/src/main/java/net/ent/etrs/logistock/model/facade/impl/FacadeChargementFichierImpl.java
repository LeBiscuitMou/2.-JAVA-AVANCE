package net.ent.etrs.logistock.model.facade.impl;

import net.ent.etrs.logistock.model.commons.validator.ValidException;
import net.ent.etrs.logistock.model.dao.DaoFactory;
import net.ent.etrs.logistock.model.dao.IDaoArticle;
import net.ent.etrs.logistock.model.dao.IDaoClient;
import net.ent.etrs.logistock.model.dao.IDaoCommande;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.EntitiesFactory;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import net.ent.etrs.logistock.model.exceptions.DaoException;
import net.ent.etrs.logistock.model.facade.IFacadeChargementFichier;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public class FacadeChargementFichierImpl implements IFacadeChargementFichier {
    private IDaoClient daoClient;

    private IDaoArticle daoArticle;

    private IDaoCommande daoCommande;

    public FacadeChargementFichierImpl() {
        daoClient = DaoFactory.getDaoClient();
        daoArticle = DaoFactory.getDaoArticle();
        daoCommande = DaoFactory.getDaoCommande();
    }

    @Override
    public void initialisation() throws BusinessException {
        initArticle();
        initClient();
        initCommande();
    }

    private void initCommande() {
    }

    private void initClient() {
    }

    private void initArticle() throws BusinessException {
        try {
            if (daoArticle.count() > 0) {
                System.out.println("Initialisation déjà faite");
            } else {
                Path path = Path.of(FacadeArticleImpl.class.getResource("/articles.csv").toURI());
                for(String line : Files.readAllLines(path)){
                    String[] lineDecoupe = line.split("\\|");

                    String nom = lineDecoupe[0];
                    BigDecimal prix = BigDecimal.valueOf(Long.parseLong(lineDecoupe[1]));
                    Emplacement emplacement = Emplacement.valueOf(lineDecoupe[2].toUpperCase());

                    Article article = EntitiesFactory.fabriquerArticle(nom, prix, emplacement);
                    daoArticle.save(article);
                }
            }
        } catch(URISyntaxException | IOException | ValidException | DaoException e){
            throw new BusinessException(e);
        }
    }
}