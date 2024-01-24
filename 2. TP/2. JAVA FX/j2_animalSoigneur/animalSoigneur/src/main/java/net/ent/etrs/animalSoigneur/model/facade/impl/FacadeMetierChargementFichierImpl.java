package net.ent.etrs.animalSoigneur.model.facade.impl;

import net.ent.etrs.animalSoigneur.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.animalSoigneur.model.facade.exceptions.BusinessException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeMetierChargementFichierImpl implements IFacadeMetierChargementFichier {
    protected FacadeMetierChargementFichierImpl() {
    }

    @Override
    public void initialisation() throws BusinessException {
        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeMetierChargementFichierImpl.class.getResource("/").toURI()));

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
            throw new BusinessException(e.getMessage(), e);
        }
    }
}