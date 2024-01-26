package net.ent.etrs.projetjavamaier.presenteur;


import net.ent.etrs.projetjavamaier.models.facade.ConsoleFacade;
import net.ent.etrs.projetjavamaier.models.facade.FabriquantFacade;
import net.ent.etrs.projetjavamaier.models.facade.FacadeChargementFichier;
import net.ent.etrs.projetjavamaier.models.facade.JeuVideoFacade;
import net.ent.etrs.projetjavamaier.models.facade.impl.FactoryFacadeMetier;


public class Presenteur {


    //FACADES
    ConsoleFacade facadeConsole;
    FabriquantFacade facadeFabriquant;
    JeuVideoFacade facadeJeuVideo;



    FacadeChargementFichier facadeChargementFichier;

    public Presenteur() {
        //construire chaque facade
        facadeConsole = FactoryFacadeMetier.fabriquerConsoleFacade();
        facadeFabriquant = FactoryFacadeMetier.fabriquerFabriquantFacade();
        facadeJeuVideo=FactoryFacadeMetier.fabriquerJeuVideoFacade();

        facadeChargementFichier = FactoryFacadeMetier.fabriquerFacadeChargementMetier();

        try {
            facadeChargementFichier.initialisation();
        } catch (Exception e) {
            System.out.println("ERR Init :" + e.getMessage());
        }
    }


}
