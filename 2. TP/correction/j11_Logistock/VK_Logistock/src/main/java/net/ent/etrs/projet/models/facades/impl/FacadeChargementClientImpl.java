package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoClient;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.EntitiesFactory;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeChargementFichier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeChargementClientImpl implements IFacadeChargementFichier {
    IDaoClient daoClient;
    protected FacadeChargementClientImpl() {
        daoClient = DaoFactory.getDaoClient();
    }

    @Override
    public void initialisation() throws BusinessException {
        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementClientImpl.class.getResource("/clients.csv").toURI()));

            for (String line : Files.readAllLines(fichierInit)) {
                String[] data = line.split("\\|");

                String nom = data[0];
                String prenom = data[1];
                String adresse = data[2];
                String codePostal = data[3];
                String ville = data[4];
                String numTel = data[5].isEmpty() ? null : data[5];

                daoClient.save(EntitiesFactory.fabriquerClient(nom, prenom, adresse, codePostal, ville, numTel));
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
