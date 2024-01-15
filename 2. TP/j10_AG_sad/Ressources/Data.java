package fr.ldek.ag.sacados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {
    
    public static final List<Objet> LISTE_OBJETS = new ArrayList<>();
    
    static {
        System.out.println("0.Chargement des données.");
//        Data.charger();
        Data.chargerAlea();
    }
    
    private static void charger() {
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Saucisson" ,200 ,4));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Brosse à dent" ,50,3));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Ration" , 500,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Casque Lourd" ,2000 ,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Munitions" , 500,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Savon" , 100,3));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Sac de couchage" ,500 ,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Bache" ,500 ,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Corde" ,5000 ,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Polaire",3000 ,3));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Treillis",2500 ,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Confiseries", 500,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Trousse de secours",200 ,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Lampe", 50,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Couteau", 100,3));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Bière", 700,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Jumelle",700 ,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Radio", 7000,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Serviettes",50 ,4));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Lingettes", 100,5));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Kit Camouflage",75 ,4));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Briquet", 25,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("T3P", 5500,5));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Sous-vetements",125 ,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Eau+Gourde",3000 ,1));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Effet pluie", 1025,3));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Kit nettoyage arme", 227,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Papier+Crayon", 32,2));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Piles", 101,3));
        LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet("Protections périodique", 22,1));
    }
    
    private static void chargerAlea() {
        Random r = new Random();
        for (int i = 0; i < 1_000_000; i++) {
            LISTE_OBJETS.add(EntitiesFactory.fabriquerObjet(String.valueOf(i) ,r.nextInt(4980)+20 ,r.nextInt(4)+1));
        }
    }
    
}
