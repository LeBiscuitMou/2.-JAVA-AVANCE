package net.ent.etrs.consoElecgaz.models.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeConso {
    ENTREPRISES("ENT"),
    PROFESSIONNELS("PRO"),
    RESIDENTIEL("RES"),
    RESIDENTIEL_PROFESSIONNELS("RES_PRO"),
    ENTREPRISES_PROFESSIONNELS("ENT_PRO");

    private final String codeConso;
}
