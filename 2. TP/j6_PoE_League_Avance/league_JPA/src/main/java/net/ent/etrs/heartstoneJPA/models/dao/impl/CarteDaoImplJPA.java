package net.ent.etrs.heartstoneJPA.models.dao.impl;

import net.ent.etrs.heartstoneJPA.models.dao.CarteDao;
import net.ent.etrs.heartstoneJPA.models.entities.Carte;
import net.ent.etrs.heartstoneJPA.models.entities.references.Classe;
import net.ent.etrs.heartstoneJPA.models.entities.references.TypeCarte;

import java.time.Month;
import java.util.List;
import java.util.Map;

public class CarteDaoImplJPA extends JpaBaseDao<Carte> implements CarteDao {
    @Override
    public List<Carte> getCarteByType(TypeCarte typeCarte) {
        return null;
    }

    @Override
    public List<Carte> getCarteByClasse(Classe classe) {
        return null;
    }

    @Override
    public Map<Month, List<Carte>> getCarteByMonth() {
        return null;
    }
}
