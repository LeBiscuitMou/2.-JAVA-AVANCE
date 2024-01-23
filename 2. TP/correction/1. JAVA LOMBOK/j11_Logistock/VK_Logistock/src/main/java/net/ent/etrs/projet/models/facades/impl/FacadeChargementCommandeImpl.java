package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoArticle;
import net.ent.etrs.projet.models.dao.IDaoClient;
import net.ent.etrs.projet.models.dao.IDaoCommande;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.Client;
import net.ent.etrs.projet.models.entities.EntitiesFactory;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeChargementFichier;
import net.ent.etrs.projet.models.references.ConstMetier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FacadeChargementCommandeImpl implements IFacadeChargementFichier {
    IDaoCommande daoCommande;
    IDaoArticle daoArticle;
    IDaoClient daoClient;
    protected FacadeChargementCommandeImpl() {
        daoCommande = DaoFactory.getDaoCommande();
        daoArticle = DaoFactory.getDaoArticle();
        daoClient = DaoFactory.getDaoClient();
    }

    @Override
    public void initialisation() throws BusinessException {
        int lineNumber = 0;

        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementCommandeImpl.class.getResource("/commandes.csv").toURI()));

           // errorLogFile = Paths.get(Objects.requireNonNull(FacadeChargementCommandeImpl.class.getResource("/errorLog.txt").toURI()));


            for (String line : Files.readAllLines(fichierInit)) {
                String[] data = line.split("\\|");

                String numCommande = data[0].trim();
                LocalDate dateCommande = LocalDate.parse(data[1].trim(), ConstMetier.DATE_FORMAT);
                String[] clientIdentity = data[2].trim().split("-");
                String clientNom = clientIdentity[0].trim();
                String clientPrenom = clientIdentity[1].trim();


                Client client = daoClient.findClientByIdentity(clientNom, clientPrenom).orElse(null);

                Map<Article, Integer> lesArticles = new HashMap<>();

                String[] dataArticles = data[3].split(";");
                for (String datum : dataArticles) {
                    System.out.println(datum);
                    if(datum.isEmpty()){
                        continue;
                    }
                    String[] donneeArticle = datum.split(",");
                    String designation = donneeArticle[0].substring(1);

                    int quantityArticle = Integer.parseInt(donneeArticle[1].substring(0, donneeArticle[1].length() - 1));

                    Article article = daoArticle.findByIdentity(designation).orElse(null);

                    lesArticles.put(article, quantityArticle);
                }

                daoCommande.save(EntitiesFactory.fabriquerCommande(numCommande, dateCommande, client, lesArticles));

                lineNumber++;
            }
        } catch (URISyntaxException | IOException | EntitiesFactoryException | DaoException e) {
            throw new BusinessException(e.getMessage() + "\nerreur ligne : " + lineNumber);
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

    }
}
