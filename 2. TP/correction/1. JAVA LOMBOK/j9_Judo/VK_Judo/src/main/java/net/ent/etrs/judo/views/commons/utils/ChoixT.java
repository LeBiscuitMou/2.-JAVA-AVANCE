package net.ent.etrs.judo.views.commons.utils;

import java.util.*;


/**
 * Collections de méthodes permettant de choisir un élément parmi un ensemble d'éléments,
 * ensemble supporté : List, Set, Array.
 */
public final class ChoixT {
    private static final String DEFAULT_MESSAGE = "choisissez judicieusement";

    /**
     * Permet de récupérer un Objet de la liste d'objet fourni.
     *
     * @param list une liste de type T
     * @param message le message à envoyer en titre du menu
     * @param <T> le type de la collection
     * @return un élément de type T choisis
     */
    public static <T> T selectFrom(List<T> list, String message) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i+1, list.get(i).toString());
        }
        AffichageConsole.afficherMenuEntoure(map, message);
        return list.get(LectureConsole.lectureChoixInt(1, list.size())-1);
    }

    /**
     * Permet de récupérer un Objet du set d'objet fourni
     *
     * @param set un set de type T
     * @param message le message à envoyer en titre du menu
     * @param <T> le type de la collection
     * @return un élément de type T choisis
     */
    public static <T> T selectFrom(Set<T> set, String message) {
        List<T> list = new ArrayList<T>(set);
        return selectFrom(list, message);
    }

    /**
     * Permet de récupérer un Objet du tableau d'objet fourni
     *
     * @param array un tableau de type T
     * @param message le message à envoyer en titre du menu
     * @param <T> le type de la collection
     * @return un élément de type T choisis
     */
    public static <T> T selectFrom(T[] array, String message) {
        List<T> list = new ArrayList<>(List.of(array));
        return selectFrom(list, message);
    }

    /**
     * Permet de récupérer un Objet de la liste d'objet fourni.
     *
     * @param list une liste de type T
     * @param <T> le type de la collection
     * @return un élément de type T choisis
     */
    public static <T> T selectFrom(List<T> list) {
        return selectFrom(list, DEFAULT_MESSAGE);
    }

    /**
     * Permet de récupérer un Objet du set d'objet fourni
     *
     * @param set un set de type T
     * @param <T> le type de la collection
     * @return un élément de type T choisis
     */
    public static <T> T selectFrom(Set<T> set) {
        return selectFrom(set, DEFAULT_MESSAGE);
    }

    /**
     * Permet de récupérer un Objet du tableau d'objet fourni
     *
     * @param array un tableau de type T
     * @param <T> le type de la collection
     * @return un élément de type T choisis
     */
    public static <T> T selectFrom(T[] array) {
        return selectFrom(array, DEFAULT_MESSAGE);
    }
}
