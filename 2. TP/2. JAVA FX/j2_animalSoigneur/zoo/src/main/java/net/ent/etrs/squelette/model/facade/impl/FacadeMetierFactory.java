package net.ent.etrs.squelette.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.facade.IFacadeMetierAnimal;
import net.ent.etrs.squelette.model.facade.IFacadeMetierChargementFichier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierAnimal fabriquerFacadeMetierAnimal() {
        return new FacadeMetierAnimalImpl();
    }

    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }
}
