package net.ent.etrs.projet.models.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Collection de méthode transformant des Itérables en Set ou List,
 * transforme des Sets en List et vice-versa.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CoUtils {
    /**
     * Transforme un itérable en liste.
     *
     * @param iterable un itérable de type T
     * @param <T> le type de la collection
     * @return une liste de type T
     */
    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> result = new ArrayList<T>();
        for (T t : iterable) {
            result.add(t);
        }
        return result;
    }

    /**
     * Transforme un itérable en set.
     *
     * @param iterable un itérable de type T
     * @param <T> le type de la collection
     * @return un set de type T
     */
    public static <T> Set<T> iterableToSet(Iterable<T> iterable) {
        Set<T> result = new HashSet<T>();
        for (T t : iterable) {
            result.add(t);
        }
        return result;
    }

    /**
     * Transforme une liste en set.
     *
     * @param list une liste de type T
     * @param <T> le type de la collection
     * @return un set de type T
     */
    public static <T> Set<T> listToSet(List<T> list) {
        return new HashSet<T>(list);
    }

    /**
     * Transforme un set en liste.
     *
     * @param set un set de type T
     * @param <T> le type de la collection
     * @return une liste de type T
     */
    public static <T> List<T> SetToList(Set<T> set) {
        return new ArrayList<T>(set);
    }


}
