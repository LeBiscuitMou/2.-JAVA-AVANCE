package net.ent.etrs.judo.models.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Categorie {
    // Catégories DAMES
    F_MOINS_48(true,0, 48),
    F_MOINS_52(true,48, 52),
    F_MOINS_57(true,52, 57),
    F_MOINS_63(true,57, 63),
    F_MOINS_70(true,63, 70),
    F_MOINS_78(true,70, 78),
    F_PLUS_78(true,78, 999999999),
    // Catégorie MESSIEURS
    M_MOINS_60(false,0, 60),
    M_MOINS_66(false,60, 66),
    M_MOINS_73(false,66, 73),
    M_MOINS_81(false,73, 81),
    M_MOINS_90(false,81, 90),
    M_MOINS_100(false,90, 100),
    M_PLUS_100(false,100, 999999999);

    private final boolean feminin;
    private final int minIncluded;
    private final int maxEcluded;

    public static Categorie get(final boolean pFeminin, final int pPoids) {
        for(final Categorie categorie : Categorie.values()){
            if(categorie.isFeminin() == pFeminin && pPoids >= categorie.minIncluded && pPoids < categorie.getMaxEcluded()){
                return categorie;
            }
        }
        return null;
    }





}
