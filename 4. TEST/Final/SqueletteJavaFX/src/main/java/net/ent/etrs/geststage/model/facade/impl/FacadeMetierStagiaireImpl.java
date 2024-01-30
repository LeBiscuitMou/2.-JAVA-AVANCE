package net.ent.etrs.geststage.model.facade.impl;

import net.ent.etrs.geststage.model.dao.IDaoStagiaire;
import net.ent.etrs.geststage.model.dao.exceptions.DaoException;
import net.ent.etrs.geststage.model.dao.impl.DaoFactory;
import net.ent.etrs.geststage.model.entities.Stagiaire;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStagiaire;
import net.ent.etrs.geststage.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierStagiaireImpl implements IFacadeMetierStagiaire {
    private IDaoStagiaire daoStagiaire;

    protected FacadeMetierStagiaireImpl() {
        daoStagiaire = DaoFactory.getDaoStagiaire();
    }

    @Override
    public void creerStagiaire(Stagiaire stagiaire) throws BusinessException {
        try {
            daoStagiaire.save(stagiaire);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerStagiaire(Stagiaire stagiaire) throws BusinessException {
        try {
            daoStagiaire.delete(stagiaire);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Stagiaire> recupererTousLesStagiaires() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoStagiaire.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}