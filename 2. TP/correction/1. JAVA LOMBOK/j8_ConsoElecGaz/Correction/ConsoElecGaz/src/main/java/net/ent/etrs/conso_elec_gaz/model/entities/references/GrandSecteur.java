package net.ent.etrs.conso_elec_gaz.model.entities.references;

public enum GrandSecteur {
    A("Agriculture"),
    T("Tertiaire"),
    I("Industrie"),
    R("Résidentiel"),
    X("Secteur Inconnu");

    private String libelle;

    GrandSecteur(String libelle) {
        this.libelle = libelle;
    }
}
