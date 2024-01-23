package net.ent.etrs.consoElecgaz.models.facades;

import net.ent.etrs.consoElecgaz.models.entities.Region;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import java.util.List;

public interface IFacadeRegion {

    Region sauvegarderRegion(Region region) throws BusinessException;

    List<Region> getAllRegions() throws BusinessException;


}
