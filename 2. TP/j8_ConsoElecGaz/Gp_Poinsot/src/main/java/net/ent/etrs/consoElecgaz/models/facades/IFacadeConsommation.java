package net.ent.etrs.consoElecgaz.models.facades;

import net.ent.etrs.consoElecgaz.models.entities.Consomation;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.Region;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;
import net.ent.etrs.consoElecgaz.models.entities.references.GrandSecteur;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IFacadeConsommation {
    Consomation sauvegarderConsomation(Consomation consomation) throws BusinessException;

    List<Consomation> consomationsBySecteur(GrandSecteur secteur) throws BusinessException;

    List<Consomation> consomationsBeforeDate(LocalDate date) throws BusinessException;

    List<Consomation> consomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws BusinessException;

    List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws BusinessException;

    Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException;

    Map<Filiere, Map<LocalDate, Double>> sumConsomationsByFiliereForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException;
}
