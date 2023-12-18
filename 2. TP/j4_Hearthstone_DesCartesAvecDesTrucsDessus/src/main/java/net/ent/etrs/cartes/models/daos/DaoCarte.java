package net.ent.etrs.cartes.models.daos;

import net.ent.etrs.cartes.models.entities.Carte;
import net.ent.etrs.cartes.models.entities.references.Classe;
import net.ent.etrs.cartes.models.entities.references.TypeCarte;

import java.util.List;

public interface DaoCarte extends BaseDao<Carte> {
    List<Carte> getCarteByType(TypeCarte typeCarte);

    List<Carte> getCarteByClasse(Classe classe);
}
