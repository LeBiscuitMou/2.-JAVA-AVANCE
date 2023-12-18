package net.ent.etrs.projet.views.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Utils {

    private Utils() {
    }

    /**
     * Permet de récupéré un Objet de la Collection d'objet fourni
     *
     * @param collection la collection d'objet fourni
     * @param <T>        Type de la collection
     * @param <C>        C la collection du type T
     * @return l'objet sélectionné
     */
    public static <T, C extends Collection<T>> T recuperationObjet(C collection) {
        List<T> list = new ArrayList<>(collection);
        AffichageConsole.afficherMenuSimple(list);
        return list.get(LectureConsole.lectureChoixInt(0, list.size()) - 1);
    }
}
