package net.ent.etrs.judo.models.facades.impl;

import net.ent.etrs.judo.models.dao.IDaoClub;
import net.ent.etrs.judo.models.dao.impl.DaoFactory;
import net.ent.etrs.judo.models.exceptions.BusinessException;
import net.ent.etrs.judo.models.facades.interfaces.FacadeChargementFichier;
import net.ent.etrs.judo.models.references.ConstMetier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeChargementFichierImpl implements FacadeChargementFichier {
    private final IDaoClub daoExemple;

    protected FacadeChargementFichierImpl() {
        daoExemple = DaoFactory.getDaoClub();
    }

    @Override
    public void initialisation() throws BusinessException {
        try {
            long count = 0L;
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/" + ConstMetier.INIT_FILE_NAME).toURI()));

            for (String line : Files.readAllLines(fichierInit)) {


                count++;
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }



}
