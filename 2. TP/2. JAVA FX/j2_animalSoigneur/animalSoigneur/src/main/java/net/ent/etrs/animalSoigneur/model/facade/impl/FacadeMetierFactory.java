package net.ent.etrs.animalSoigneur.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.animalSoigneur.model.facade.IFacadeMetierAnimal;
import net.ent.etrs.animalSoigneur.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.animalSoigneur.model.facade.IFacadeMetierSoigneur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierAnimal fabriquerFacadeMetierAnimal() {
        return new FacadeMetierAnimalImpl();
    }
    
    public static IFacadeMetierSoigneur fabriquerFacadeMetierSoigneur() {
        return new FacadeMetierSoigneurImpl();
    }
}
