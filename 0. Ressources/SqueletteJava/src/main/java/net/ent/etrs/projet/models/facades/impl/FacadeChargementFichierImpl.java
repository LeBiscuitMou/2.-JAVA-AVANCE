package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoExemple;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.interfaces.FacadeChargementFichier;
import net.ent.etrs.projet.models.references.ConstMetier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeChargementFichierImpl implements FacadeChargementFichier {
    protected FacadeChargementFichierImpl() {

    }

    @Override
    public void initialisation() throws BusinessException {
        try {
            long count = 0L;
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/" + ConstMetier.INIT_FILE_NAME).toURI()));

            for (String line : Files.readAllLines(fichierInit)) {
                String[] data = line.split(";");

                count++;
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }



}
