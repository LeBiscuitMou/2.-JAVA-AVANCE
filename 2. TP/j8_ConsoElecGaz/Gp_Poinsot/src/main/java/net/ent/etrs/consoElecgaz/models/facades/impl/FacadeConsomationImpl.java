package net.ent.etrs.consoElecgaz.models.facades.impl;

import net.ent.etrs.consoElecgaz.models.daos.DaoConsommation;
import net.ent.etrs.consoElecgaz.models.daos.DaoFactory;
import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Consomation;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.Region;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;
import net.ent.etrs.consoElecgaz.models.entities.references.GrandSecteur;
import net.ent.etrs.consoElecgaz.models.facades.IFacadeConsommation;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FacadeConsomationImpl implements IFacadeConsommation {
    DaoConsommation daoConsommation;

    public FacadeConsomationImpl() {
        daoConsommation = DaoFactory.getDaoConsommation();
    }

    @Override
    public Consomation sauvegarderConsomation(Consomation consomation) throws BusinessException {
        try {
            return daoConsommation.save(consomation);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Consomation> consomationsBySecteur(GrandSecteur secteur) throws BusinessException {
        try {
            return daoConsommation.findAllConsomationsBySecteur(secteur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Consomation> consomationsBeforeDate(LocalDate date) throws BusinessException {


        try {
            return daoConsommation.consomationsBeforeDate(date);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Consomation> consomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws BusinessException {
        try {
            return daoConsommation.findAllConsomationsByRegionsAndBeforeDate(lesRegions, date);
        } catch (Exception e) {
            throw new BusinessException(e);
        }


    }

    @Override
    public List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws BusinessException {
        try {
            return daoConsommation.consomationsByOperateurAndAfterDate(operateur, date);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException {
        return null;
    }

    @Override
    public Map<Filiere, Map<LocalDate, Double>> sumConsomationsByFiliereForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException {
        try {
            return daoConsommation.sumConsomationsByFiliereForRegionAndBeforeDate(region, date);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}