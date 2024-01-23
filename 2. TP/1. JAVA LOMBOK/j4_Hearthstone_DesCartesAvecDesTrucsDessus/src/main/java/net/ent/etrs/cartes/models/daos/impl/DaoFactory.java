package net.ent.etrs.cartes.models.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.cartes.models.daos.DaoCaracteristique;
import net.ent.etrs.cartes.models.daos.DaoCarte;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    public static DaoCarte fabriquerDaoCarte() {
        return new DaoCarteImpl();
    }

    public static DaoCaracteristique fabriquerDaoCaracteristique() {
        return new DaoCaracteristiqueImpl();
    }
}
