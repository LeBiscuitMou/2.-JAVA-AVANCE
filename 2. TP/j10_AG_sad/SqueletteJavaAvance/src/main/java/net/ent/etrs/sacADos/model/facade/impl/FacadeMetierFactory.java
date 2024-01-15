package net.ent.etrs.sacADos.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.sacADos.model.facade.IFacadeMetierExemple;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierExemple fabriquerFacadeMetierExemple() {
        return new FacadeMetierExemple();
    }
}
