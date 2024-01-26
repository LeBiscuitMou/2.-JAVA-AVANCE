package net.ent.etrs.projet.models.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.entities.references.Type;
import net.ent.etrs.projet.models.facades.FacadeMetier;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeAnimal;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeChargementFichier;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeSoigneur;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }

    public static IFacadeChargementFichier fabriquerFacadeChargementFichier(){
        return new FacadeChargementFichierImpl();
    }

    public static IFacadeAnimal fabriquerFacadeAnimal(){
        return new FacadeAnimalImpl();
    }

    public static IFacadeSoigneur fabriquerFacadeSoigneur(){
        return new FacadeSoigneurImpl();
    }
}
