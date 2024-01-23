package net.ent.etrs.heartstoneJPA.models.dao;



import net.ent.etrs.heartstoneJPA.models.entities.Carte;
import net.ent.etrs.heartstoneJPA.models.entities.references.Classe;
import net.ent.etrs.heartstoneJPA.models.entities.references.TypeCarte;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface CarteDao extends  BaseDao<Carte>{


    List<Carte> getCarteByType(TypeCarte typeCarte);

    List<Carte> getCarteByClasse(Classe classe);

    Map<Month, List<Carte>> getCarteByMonth();
}
