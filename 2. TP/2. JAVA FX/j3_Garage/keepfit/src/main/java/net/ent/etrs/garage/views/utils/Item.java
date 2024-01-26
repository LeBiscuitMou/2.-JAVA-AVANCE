package net.ent.etrs.garage.views.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "object")
public class Item<T> {

    @Getter
    private T object;

    @Getter
    private String libelle;

    public Item(T object, String libelle) {
        this.object = object;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
