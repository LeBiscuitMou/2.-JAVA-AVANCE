package net.ent.etrs.projet.views.commons.console;

public final class AffichageConsole2 {
    /**
     * Affiche une grille de String, il faut que tous les strings sur une même colonne aient la même taille.
     * @param grille la grille qui sera affichée
     */
    public static void afficherGrille(String[][] grille){
        StringBuilder affichage = new StringBuilder();
        for (String[] line: grille) {
            affichage.append(afficherInterlineGrille(line));
            affichage.append('|');
            for (String text : line) {
                affichage.append(String.format(" %s |", text));
            }
            System.out.println(affichage);
            affichage.delete(0, affichage.length());
        }
        System.out.println(afficherInterlineGrille(grille[0]));
    }

    /**
     * Affiche une grille de String, il faut que tous les strings sur une même colonne aient la même taille.
     * @param grille la grille qui sera affichée
     */
    public static void afficherGrilleAvecIndice(String[][] grille){
        StringBuilder affichage = new StringBuilder();
        affichage.append("   |");
        for (int i = 0; i < grille[0].length; i++) {
            affichage.append(" ".repeat(grille[0][i].length()/2));
            affichage.append(String.format("%s", createIndiceText(i+1)));
            affichage.append(" ".repeat((grille[0][i].length()-1)/2));
            affichage.append('|');
        }
        affichage.append('\n');
        int i = 1;
        for (String[] line: grille) {
            affichage.append(afficherInterlineGrilleAvecIndice(line));
            affichage.append(String.format("%s|", createIndiceText(i++)));
            for (String text : line) {
                affichage.append(String.format(" %s |", text));
            }
            System.out.println(affichage);
            affichage.delete(0, affichage.length());
        }
        System.out.println(afficherInterlineGrilleAvecIndice(grille[0]));
    }

    public static void afficherGrille(char[][] grille) {
        StringBuilder affichage = new StringBuilder();
        for (char[] line: grille) {
            affichage.append(afficherInterlineGrille(line));
            affichage.append('|');
            for (char text : line) {
                affichage.append(String.format(" %c |", text));
            }
            System.out.println(affichage);
            affichage.delete(0, affichage.length());
        }
        System.out.println(afficherInterlineGrille(grille[0]));
    }

    public static void afficherGrilleAvecIndice(char[][] grille) {
        StringBuilder affichage = new StringBuilder();
        affichage.append("   |");
        for (int i = 0; i < grille[0].length; i++) {
            affichage.append(String.format("%s|", createIndiceText(i+1)));
        }
        affichage.append('\n');
        int i = 1;
        for (char[] line: grille) {
            affichage.append(afficherInterlineGrilleAvecIndice(line));
            affichage.append(String.format("%s|", createIndiceText(i++)));
            for (char text : line) {
                affichage.append(String.format(" %c |", text));
            }
            System.out.println(affichage);
            affichage.delete(0, affichage.length());
        }
        System.out.println(afficherInterlineGrilleAvecIndice(grille[0]));
    }

    private static String afficherInterlineGrille(String[] line){
        StringBuilder affichage = new StringBuilder();
        affichage.append('|');
        for (String text : line) {
            affichage.append("-".repeat(text.length()+2));
            affichage.append("|");
        }
        affichage.append('\n');
        return affichage.toString();
    }

    private static String afficherInterlineGrilleAvecIndice(String[] line){
        return "---" + afficherInterlineGrille(line);
    }

    private static String afficherInterlineGrille(char[] line){
        String affichage = '|' +
                "---|".repeat(line.length);
        return affichage + '\n';
    }

    private static String afficherInterlineGrilleAvecIndice(char[] line){
        return "---" + afficherInterlineGrille(line);
    }

    private static String createIndiceText(int number){
        if (number < 10){
            return String.format("%2d ", number);
        }
        return String.format("%3d", number);
    }

    /**
     * Convertie un nombre en base 10 vers une chaine de caractère représentant un nombre en base 26, constituer des
     * lettres de l'alphabet, en majuscule, A → 0, Z → 25.
     * @param numberBase10 un nombre en base 10
     * @return une chaine de caractère constitué de lettres majuscule qui représente un nombre en base 26
     */
    public static String convert10To26(int numberBase10){
        if (numberBase10 < 26){
            // 65 code ascii = A, 65+25(valeur max) = 90 code ascii Z
            return String.format("%c", numberBase10+65);
        }
        int divisionEuclidienne = (numberBase10/26)-1;
        int resteDivEuclid = (numberBase10%26)+65;
        return convert10To26(divisionEuclidienne) + String.format("%c", resteDivEuclid);
    }


}
