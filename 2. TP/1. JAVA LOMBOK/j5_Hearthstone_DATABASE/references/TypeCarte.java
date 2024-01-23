package src.main.java.net.ent.etrs.models.references;

public enum TypeCarte {

    SERVITEURS("qui invoquent une créature sur le terrain de jeu. Un serviteur possède une valeur d'attaque (indiquée en bas à gauche), un nombre de points de vie (indiqué en bas à droite) et éventuellement une action spéciale"),
    SORTS("qui effectuent une ou plusieurs actions spéciales"),
    ARMES("permettent au héros d'attaquer en plus de ses serviteurs.") ,

    HEROS_LEGENDAIRES("qui se substituent aux héros standards. Lorsqu'une carte de ce type est jouée"),

    ORDINAIRES("faire des dégâts, soigner, piocher des cartes, voler des cartes à l'adversaire, etc.") ,

    SECRETS("des cartes qui préparent une action inconnue de l'adversaire et qui se déclenche pendant son tour, sous certaines conditions (jouer un serviteur, un sort, utiliser son pouvoir héroïque, etc."),
    QUETES_DE_CLASSE("les cartes de « quêtes » jouées en début de partie pour un point de mana, permettant de recevoir une récompense après avoir complété la quête plusieurs tours après");

    private final String desTypeCarte;

    TypeCarte(String desTypeCarte) {
        this.desTypeCarte = desTypeCarte;
    }

    public String getDesTypeCarte() {
        return desTypeCarte;
    }


}
