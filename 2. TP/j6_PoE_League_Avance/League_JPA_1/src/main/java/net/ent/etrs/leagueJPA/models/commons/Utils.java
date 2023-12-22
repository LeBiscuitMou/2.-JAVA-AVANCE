package net.ent.etrs.leagueJPA.models.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> result = new ArrayList<T>();
        for (T t : iterable) {
            result.add(t);
        }
        return result;
    }

    public static <T> Set<T> iterableToSet(Iterable<T> iterable) {
        Set<T> result = new HashSet<>();
        for (T t : iterable) {
            result.add(t);
        }
        return result;
    }
}
