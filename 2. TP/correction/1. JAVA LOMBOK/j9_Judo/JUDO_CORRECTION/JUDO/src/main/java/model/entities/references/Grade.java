package model.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {
    
    KYU_9("9ème kyu", "Blanche",6),
    KYU_8("8ème kyu", "Blanche-jaune",7),
    KYU_7("7ème kyu", "Jaune",8),
    KYU_6("6ème kyu", "Jaune-orange",9),
    KYU_5("5ème kyu", "Orange",10),
    KYU_4("4ème kyu", "Orange-verte",11),
    KYU_3("3ème kyu", "Verte",12),
    KYU_2("2ème kyu", "Bleue",13),
    KYU_1("1er kyu", "Marron",14),
    DAN_1("1ère dan", "Noire",15),
    DAN_2("2ème dan", "Noire",17),
    DAN_3("3ème dan", "Noire",20),
    DAN_4("4ème dan", "Noire",24),
    DAN_5("5ème dan", "Noire",29),
    DAN_6("6ème dan", "Rouge-blanche",29),
    DAN_7("7ème dan", "Rouge-blanche",29),
    DAN_8("8ème dan", "Rouge-blanche",29),
    DAN_9("9ème dan", "Rouge",29),
    DAN_10("10ème dan", "Rouge",29);
    
    private final String nom;
    private final String couleur;
    private final int ageMinimum;

    /**
     * Méthode permettant à partir du grade actuel de renvoyer le grade supérieur.
     * @return le grade supérieur
     */
    public Grade next() {
        final Grade[] grades = values();
        return this.ordinal() != grades.length - 1 ? grades[this.ordinal() + 1] : null;
    }
}
