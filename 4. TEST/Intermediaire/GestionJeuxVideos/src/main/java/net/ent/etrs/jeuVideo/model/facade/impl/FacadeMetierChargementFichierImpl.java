package net.ent.etrs.jeuVideo.model.facade.impl;

import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierConsole;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierFabriquant;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierJeuVideo;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FacadeMetierChargementFichierImpl implements IFacadeMetierChargementFichier {
    private IFacadeMetierConsole facadeMetierConsole;
    private IFacadeMetierFabriquant facadeMetierFabriquant;
    private IFacadeMetierJeuVideo facadeMetierJeuVideo;

    public FacadeMetierChargementFichierImpl() {
        facadeMetierConsole = FacadeMetierFactory.fabriquerFacadeMetierConsole();
        facadeMetierFabriquant = FacadeMetierFactory.fabriquerFacadeMetierFabriquant();
        facadeMetierJeuVideo = FacadeMetierFactory.fabriquerFacadeMetierJeuVideo();
    }

    /**
     * Permet d'initialisaer les données du logiciel
     */
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