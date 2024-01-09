package net.ent.etrs.consoElecgaz.models.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrandSecteur {

    TERTIAIRE("T"),
    AGRICULTURE("A"),
    IDUSTRIE("A"),
    RESIDENTIELE("R"),
    AUTRES("X");

    private final String codeGrandSecteur;
}
