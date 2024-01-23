package net.ent.etrs.conso_elec_gaz.model.daos;

import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.*;
import net.ent.etrs.conso_elec_gaz.model.entities.references.Filiere;
import net.ent.etrs.conso_elec_gaz.model.entities.references.GrandSecteur;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DaoConsomation extends BaseDao<Consomation> {
    List<Consomation> consomationsBySecteur(GrandSecteur secteur) throws DaoException;

    List<Consomation> consomationsBeforeDate(LocalDate date) throws DaoException;

    List<Consomation> consomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws DaoException;

    List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws DaoException;

    Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws DaoException;

    Map<Filiere, Map<LocalDate, Double>> sumConsomationsByFiliereForRegionAndBeforeDate(Region region, LocalDate date) throws DaoException;
}
