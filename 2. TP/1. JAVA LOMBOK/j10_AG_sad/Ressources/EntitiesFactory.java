package fr.ldek.ag.sacados;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntitiesFactory {
    
    public static Objet fabriquerObjet(String pNom, Integer pPoids, Integer pUtilite) {
        Objet o = new Objet();
        o.setNom(pNom);
        o.setPoids(pPoids);
        o.setUtilite(pUtilite);
        return o;
    }
    
}
