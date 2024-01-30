package net.ent.etrs.geststage.models.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Matiere {
    SSI(1),
    ALGO(2),
    HTML_CSS(2),
    JAVASCRIPT(2),
    COO(2),
    JAVA_BASE(3),
    JAVA_AVANCE(3),
    JAVA_EE(3),
    SQL(2),
    BDD(2),
    ANASI(2);

    @Getter
    private Integer coefficient;
}
