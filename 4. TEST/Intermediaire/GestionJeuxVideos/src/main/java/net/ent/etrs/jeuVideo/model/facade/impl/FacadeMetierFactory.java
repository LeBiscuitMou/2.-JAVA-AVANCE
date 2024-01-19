package net.ent.etrs.jeuVideo.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierConsole;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierFabriquant;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierJeuVideo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierConsole fabriquerFacadeMetierConsole() {
        return new FacadeMetierConsoleImpl();
    }

    public static IFacadeMetierFabriquant fabriquerFacadeMetierFabriquant() {
        return new FacadeMetierFabriquantImpl();
    }

    public static IFacadeMetierJeuVideo fabriquerFacadeMetierJeuVideo() {
        return new FacadeMetierJeuVideoImpl();
    }

    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }
}
