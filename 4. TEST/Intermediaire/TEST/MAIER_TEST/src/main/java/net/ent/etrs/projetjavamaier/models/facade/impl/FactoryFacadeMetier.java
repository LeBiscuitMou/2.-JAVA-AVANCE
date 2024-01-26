package net.ent.etrs.projetjavamaier.models.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projetjavamaier.models.facade.ConsoleFacade;
import net.ent.etrs.projetjavamaier.models.facade.FabriquantFacade;
import net.ent.etrs.projetjavamaier.models.facade.FacadeChargementFichier;
import net.ent.etrs.projetjavamaier.models.facade.JeuVideoFacade;
import net.ent.etrs.projetjavamaier.models.facade.impl.FacadeChargementFichierImpl;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class FactoryFacadeMetier {


    /**
     * Créer une instance singleton de facade generique.
     */
    private static FacadeChargementFichier facadeChargementMetierInstance;

    public static FacadeChargementFichier fabriquerFacadeChargementMetier() {
        if (Objects.isNull(facadeChargementMetierInstance)) {
            facadeChargementMetierInstance = new FacadeChargementFichierImpl();
        }
        return facadeChargementMetierInstance;
    }

    /**
     * Créer une instance singleton de facade entities pour chaque facade, a part facade metier car elle est abastract.
     */


     /* ******************************** FACADE JEU VIDEO ******************************** */

    private static JeuVideoFacade facadeJeuVideo;

    public static JeuVideoFacade fabriquerJeuVideoFacade() {
        if (Objects.isNull(facadeJeuVideo)) {
            facadeJeuVideo = new FacadeJeuVideoImpl();
        }
        return facadeJeuVideo;
    }


     /* ******************************** FACADE CONSOLE ******************************** */

    private static ConsoleFacade facadeConsole;

    public static ConsoleFacade fabriquerConsoleFacade() {
        if (Objects.isNull(facadeConsole)) {
            facadeConsole = new FacadeConsoleImpl();
        }
        return facadeConsole;
    }

     /* ******************************** FACADE FABRIQUANT ******************************** */

    private static FabriquantFacade facadeFabriquant;

    public static FabriquantFacade fabriquerFabriquantFacade() {
        if (Objects.isNull(facadeFabriquant)) {
            facadeFabriquant = new FacadeFabriquantImpl();
        }
        return facadeFabriquant;
    }




}