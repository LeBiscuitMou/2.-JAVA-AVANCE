package net.ent.etrs.heartStone.models.dao;

import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface CarteDao extends  BaseDao<Carte>{


    List<Carte> getCarteByType(TypeCarte typeCarte);

    List<Carte> getCarteByClasse(Classe classe);

    Map<Month, List<Carte>> getCarteByMonth();
}
