package net.ent.etrs.projet.models.entities.references;

public enum TypeSpecialite {
    SPRINTER("Je suis relativement lourd et je cherche la victoire. Je mets à profit ma grande puissance en fin d’épreuve dans le but de gagner l’étape."),
    ROULEUR("Je suis grand et adepte de la vitesse et des contre-la-montre."),
    BAROUDEUR("Je m’échappe du peloton sur de longues distances."),
    PUNCHEUR("Je dynamise le peloton et j’attaque les côtes courtes avec confiance."),
    GRIMPEUR("Ayant un gabarit léger, je suis adepte des routes montagneuses."),
    DESCENDEUR("Je suis très adroit dans les descentes, et j’en profite pour créer de l’écart."),
    FLAHUTE("La pluie, le vent… je dompte les pavés et le mauvais temps."),
    DOMESTIQUE("Je suis dévoué au leader qui ne pourrait pas réussir sans mon aide."),
    LEADER("Je suis le cycliste désigné au sein de l’équipe comme ayant le meilleur potentiel pour remporter l’épreuve, la compétition.");

    private String definition;

    TypeSpecialite(String definition) {
        this.definition = definition;
    }
}
