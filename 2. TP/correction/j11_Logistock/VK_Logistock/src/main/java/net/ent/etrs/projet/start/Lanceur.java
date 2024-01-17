package net.ent.etrs.projet.start;

import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.Commande;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeChargementFichier;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeCommande;

import java.util.Set;
import java.util.TreeSet;

public class Lanceur {
    public static void main(String[] args) {

        IFacadeChargementFichier chargerClient = FacadeMetierFactory.fabriquerFacadeChargementClient();
        IFacadeChargementFichier chargerArticle = FacadeMetierFactory.fabriquerFacadeChargementArticle();
        IFacadeChargementFichier chargerCommande = FacadeMetierFactory.fabriquerFacadeChargementCommande();

        /*
        try {
            chargerClient.initialisation();
            chargerArticle.initialisation();
            chargerCommande.initialisation();
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }*/

        IFacadeCommande facadeCommande = FacadeMetierFactory.fabriquerFacadeCommande();

        try {
            Set<Article> articleCommande1 = facadeCommande.getCommandeById(1L).get().getArticles().keySet();
            TreeSet<Commande> set = new TreeSet<>(facadeCommande.getAllCommandeByAllArticle(articleCommande1));


            if(set.isEmpty()){
                System.out.println("c'est vide");
            }
            for(Commande commande : set){
                System.out.println(commande.toString());
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }




    }
    /* ******************************** VARIABLES ******************************** */

    /* ******************************* CONSTRUCTOR ******************************* */

    /* ********************************* GETTERS ********************************* */

    /* ********************************* SETTERS ********************************* */

    /* ******************************** FONCTIONS ******************************** */

    /* **************************** PRIVATE FUNCTIONS **************************** */
}
