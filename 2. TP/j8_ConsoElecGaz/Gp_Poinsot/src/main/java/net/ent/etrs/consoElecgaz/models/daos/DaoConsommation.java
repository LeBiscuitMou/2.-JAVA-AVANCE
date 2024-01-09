package net.ent.etrs.consoElecgaz.models.daos;

import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Consomation;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.Region;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;
import net.ent.etrs.consoElecgaz.models.entities.references.GrandSecteur;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DaoConsommation extends BaseDao<Consomation, Serializable> {
    List<Consomation> findAllConsomationsBySecteur(GrandSecteur secteur) throws DaoException;

    List<Consomation> consomationsBeforeDate(LocalDate date) throws DaoException;

    List<Consomation> findAllConsomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws DaoException;

    Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException;

    List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws DaoException;

    Map<Filiere, Map<LocalDate, Double>> sumConsomationsByFiliereForRegionAndBeforeDate(Region region, LocalDate date) throws DaoException;

}
