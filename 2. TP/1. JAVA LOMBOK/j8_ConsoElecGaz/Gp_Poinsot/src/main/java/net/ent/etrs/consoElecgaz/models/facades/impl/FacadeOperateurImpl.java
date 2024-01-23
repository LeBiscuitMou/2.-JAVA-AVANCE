package net.ent.etrs.consoElecgaz.models.facades.impl;

import net.ent.etrs.consoElecgaz.models.daos.DaoFactory;
import net.ent.etrs.consoElecgaz.models.daos.DaoOperateur;
import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;
import net.ent.etrs.consoElecgaz.models.facades.IFacadeOperateur;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import java.util.List;

public class FacadeOperateurImpl implements IFacadeOperateur {
    DaoOperateur daoOperateur;

    public FacadeOperateurImpl() {
        this.daoOperateur = DaoFactory.getDaoOperateur();
    }

    @Override
    public Operateur sauvegarderOperateur(Operateur operateur) throws BusinessException {
        try {
            return daoOperateur.save(operateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }


    @Override
    public List<Operateur> operateurByFiliere(Filiere filiere) throws BusinessException {

        try {
            return daoOperateur.operateurByFiliere(filiere);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }

    }


}