package net.ent.etrs.animalSoigneur.model.dao.impl;

import net.ent.etrs.animalSoigneur.model.dao.IDaoSoigneur;
import net.ent.etrs.animalSoigneur.model.dao.base.JpaBaseDao;
import net.ent.etrs.animalSoigneur.model.entities.Soigneur;

import java.util.Optional;

public class DaoSoigneurImpl extends JpaBaseDao<Soigneur> implements IDaoSoigneur {
    @Override
    public Soigneur modifierSoigneur(Soigneur pSoigneur) {
        return null;
    }

    @Override
    public Optional<Soigneur> recupererSoigneurById(Long pId) {
        return Optional.empty();
    }
}