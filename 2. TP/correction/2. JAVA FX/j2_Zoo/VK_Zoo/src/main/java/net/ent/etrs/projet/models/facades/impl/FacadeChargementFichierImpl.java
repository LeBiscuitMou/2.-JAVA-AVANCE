package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeChargementFichier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeChargementFichierImpl implements IFacadeChargementFichier {
    protected FacadeChargementFichierImpl() {

    }

    @Override
    public void initialisation() throws BusinessException {

        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/").toURI()));

            for (String line : Files.readAllLines(fichierInit)) {
                String[] data = line.split(";");

                String var0 = data[0].trim();
                String var1 = data[1].trim();
                String var2 = data[2].trim();
                String var3 = data[3].trim();
                String var4 = data[4].trim();
                String var5 = data[5].trim();
                String var6 = data[6].trim();
                String var7 = data[7].trim();


            }
        } catch (URISyntaxException | IOException e) {
            throw new BusinessException(e);
        }

    }



}
