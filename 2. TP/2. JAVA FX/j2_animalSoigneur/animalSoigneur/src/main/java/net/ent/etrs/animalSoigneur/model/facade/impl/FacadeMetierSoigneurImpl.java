package net.ent.etrs.animalSoigneur.model.facade.impl;

import net.ent.etrs.animalSoigneur.model.dao.IDaoSoigneur;
import net.ent.etrs.animalSoigneur.model.dao.exceptions.DaoException;
import net.ent.etrs.animalSoigneur.model.dao.impl.DaoFactory;
import net.ent.etrs.animalSoigneur.model.entities.Soigneur;
import net.ent.etrs.animalSoigneur.model.facade.IFacadeMetierSoigneur;
import net.ent.etrs.animalSoigneur.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FacadeMetierSoigneurImpl implements IFacadeMetierSoigneur {
    private IDaoSoigneur daoSoigneur;

    public FacadeMetierSoigneurImpl() {
        daoSoigneur = DaoFactory.getDaoSoigneur();
    }

    @Override
    public Soigneur creerSoigneur(Soigneur pSoigneur) throws BusinessException {
        try {
            return daoSoigneur.save(pSoigneur);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerSoigneur(Soigneur pSoigneur) throws BusinessException {
        try {
            daoSoigneur.delete(pSoigneur.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Soigneur modifierSoigneur(Soigneur pSoigneur) {
        return daoSoigneur.modifierSoigneur(pSoigneur);
    }

    @Override
    public List<Soigneur> recupererTousLesSoigneurs() throws BusinessException {
        try {
            return Collections.unmodifiableList(IterableUtils.toList(daoSoigneur.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Soigneur> recupererSoigneurById(Long pId) {
        return daoSoigneur.recupererSoigneurById(pId);
    }
}