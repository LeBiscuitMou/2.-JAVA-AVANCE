package net.ent.etrs.squelette.views.utils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(of = "object")
public class Item<T> {

    @Getter
    private T object;

    @Getter
    private String libelle;

    @Override
    public String toString() {
        return libelle;
    }
}
