package net.ent.etrs.heartStone.models.dao.mem.impl;

import net.ent.etrs.heartStone.models.dao.CarteDao;
import net.ent.etrs.heartStone.models.dao.mem.MemBaseDao;
import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import java.time.Month;
import java.util.*;

public class CarteDaoImpl extends MemBaseDao<Carte> implements CarteDao {

    @Override
    public List<Carte> getCarteByType(TypeCarte typeCarte) {
        return persist.values().stream().filter(c -> c.getTypeCarte().equals(typeCarte)).toList();
    }

    @Override
    public List<Carte> getCarteByClasse(Classe classe) {
        return persist.values().stream().filter(c -> c.getClasse().equals(classe)).toList();
    }

    @Override
    public Map<java.time.Month, List<Carte>> getCarteByMonth() {
        Map<java.time.Month, List<Carte>> retour = new TreeMap<>();
        for (Month m : Month.values()) {
            List<Carte> retourCarte = new ArrayList<>();
            for (Carte c : persist.values()) {
                if (c.getDateSortie().getMonth().equals(m)) {
                    retourCarte.add(c);
                }
            }
            retour.put(m, retourCarte);
        }
        return retour;
    }


}
