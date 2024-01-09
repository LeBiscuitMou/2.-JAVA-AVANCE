package net.ent.etrs.consoElecgaz.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {


    public static Region fabriquerRegion(String libelle, Integer codeRegion) {
        Region region = new Region();
        region.setLibelle(libelle);
        region.setCodeRegion(codeRegion);
        return region;
    }
}
