package net.ent.etrs.conso_elec_gaz.model.entities.references;

public enum CategorieConsomateur {
    ENT ("Entreprises"),
    RES("Résidentiels"),
    PRO("Petits professionels"),
    ENT_PRO("Entreprises Professionelles"),
    RES_PRO("Résidences + Petits professionels"),
    X("Inconnu");

    private String libelle;

    CategorieConsomateur(String libelle) {
        this.libelle = libelle;
    }
}
