package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoArticle;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.EntitiesFactory;
import net.ent.etrs.projet.models.entities.references.Emplacement;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeChargementFichier;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeChargementArticleImpl implements IFacadeChargementFichier {
    IDaoArticle daoArticle;
    protected FacadeChargementArticleImpl() {
        daoArticle = DaoFactory.getDaoArticle();
    }

    @Override
    public void initialisation() throws BusinessException {
        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementArticleImpl.class.getResource("/articles.csv").toURI()));

            for (String line : Files.readAllLines(fichierInit)) {
                String[] data = line.split("\\|");

                String nom = data[0].trim();
                double prix = Double.parseDouble(data[1].trim());
                Emplacement emp = Emplacement.valueOf(data[2].trim());
                daoArticle.save(EntitiesFactory.fabriquerArticle(nom, BigDecimal.valueOf(prix), emp));
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            initialisation();
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }
}
