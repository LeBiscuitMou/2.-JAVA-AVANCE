package net.ent.etrs.animalSoigneur.model.dao;

import net.ent.etrs.animalSoigneur.model.dao.base.BaseDao;
import net.ent.etrs.animalSoigneur.model.entities.Soigneur;

import java.util.Optional;

public interface IDaoSoigneur extends BaseDao<Soigneur> {

    Soigneur modifierSoigneur(Soigneur pSoigneur);

    Optional<Soigneur> recupererSoigneurById(Long pId);
}