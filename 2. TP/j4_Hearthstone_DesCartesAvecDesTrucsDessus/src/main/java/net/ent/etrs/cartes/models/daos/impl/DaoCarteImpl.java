package net.ent.etrs.cartes.models.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.cartes.models.daos.DaoCarte;
import net.ent.etrs.cartes.models.daos.MemBaseDao;
import net.ent.etrs.cartes.models.entities.Carte;
import net.ent.etrs.cartes.models.entities.references.Classe;
import net.ent.etrs.cartes.models.entities.references.TypeCarte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DaoCarteImpl extends MemBaseDao<Carte> implements DaoCarte {
    @Override
    public List<Carte> getCarteByType(TypeCarte typeCarte) {
        List<Carte> carteList = new ArrayList<>();
        for (Carte carte : persist.values()) {
            if (carte.getTypeCarte().equals(typeCarte)) {
                carteList.add(carte);
            }
        }
        return Collections.unmodifiableList(carteList);
    }

    @Override
    public List<Carte> getCarteByClasse(Classe classe) {
        List<Carte> carteList = new ArrayList<>();
        for (Carte carte : persist.values()) {
            if (carte.getClasse().equals(classe)) {
                carteList.add(carte);
            }
        }
        return Collections.unmodifiableList(carteList);
    }
}