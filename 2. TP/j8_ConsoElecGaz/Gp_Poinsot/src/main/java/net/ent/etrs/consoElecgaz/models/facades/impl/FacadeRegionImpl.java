package net.ent.etrs.consoElecgaz.models.facades.impl;

import net.ent.etrs.consoElecgaz.models.daos.DaoFactory;
import net.ent.etrs.consoElecgaz.models.daos.DaoRegion;
import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Region;
import net.ent.etrs.consoElecgaz.models.facades.IFacadeRegion;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.List;

public class FacadeRegionImpl implements IFacadeRegion {
    DaoRegion daoRegion;

    public FacadeRegionImpl() {
        this.daoRegion = DaoFactory.getDaoRegion();
    }


    @Override
    public Region sauvegarderRegion(Region region) throws BusinessException {
        try {
            return daoRegion.save(region);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Region> getAllRegions() throws BusinessException {
        try {
            return new ArrayList<>(IterableUtils.toList(daoRegion.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}