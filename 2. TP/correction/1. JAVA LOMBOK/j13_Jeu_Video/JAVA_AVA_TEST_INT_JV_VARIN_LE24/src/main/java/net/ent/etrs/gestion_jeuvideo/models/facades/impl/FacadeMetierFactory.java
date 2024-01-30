package net.ent.etrs.gestion_jeuvideo.models.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.gestion_jeuvideo.models.facades.FacadeMetier;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeChargementFichier;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeConsole;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeFabriquant;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeJeuVideo;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {

    public static IFacadeChargementFichier fabriquerFacadeChargementFichier(){
        return new FacadeChargementFichierImpl();
    }

    public static IFacadeConsole fabriquerFacadeConsole(){
        return new FacadeConsoleImpl();
    }

    public static IFacadeJeuVideo fabriquerFacadeJeuVideo(){
        return new FacadeJeuVideoImpl();
    }

    public static IFacadeFabriquant fabriquerFacadeFabriquant(){
        return new FacadeFabriquantImpl();
    }
}
