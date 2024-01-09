package net.ent.etrs.consoElecgaz.models.daos.impl;

import net.ent.etrs.consoElecgaz.models.daos.DaoConsommation;
import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Consomation;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.Region;
import net.ent.etrs.consoElecgaz.models.entities.references.GrandSecteur;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoImplConsommation extends AbstractJpaDao<Consomation, Serializable> implements DaoConsommation {
    @Override
    public List<Consomation> findAllConsomationsBySecteur(GrandSecteur secteur) throws DaoException {

        try {
            TypedQuery<Consomation> tp = this.em.createQuery(
                    "SELECT c.codeConso FROM Consomation c WHERE c.secteur = :secteur", Consomation.class);
            tp.setParameter("secteur", secteur);
            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage());
        }

    }

    @Override
    public List<Consomation> consomationsBeforeDate(LocalDate date) throws DaoException {
        try {
            TypedQuery<Consomation> tp = this.em.createQuery("SELECT c FROM Consomation c WHERE c.annee < :date", Consomation.class);
            tp.setParameter("date", date);
            return tp.getResultList();

        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }


    @Override
    public List<Consomation> findAllConsomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws DaoException {
        try {
            TypedQuery<Consomation> tp = this.em.createQuery("SELECT c FROM Consomation c WHERE c.region = :region AND c.annee <:date", Consomation.class);
            tp.setParameter("region", lesRegions);
            tp.setParameter("date", date);

            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException("Erreur lors de la récupération des consommations pour les régions et avant la date : " + date, e);
        }
    }

    @Override
    public List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws DaoException {
        try {
            TypedQuery<Consomation> tp = this.em.createQuery("SELECT c FROM Consomation c WHERE c.operateur = :operateur AND c.annee >:date", Consomation.class);
            tp.setParameter("operateur", operateur);
            tp.setParameter("date", date);

            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException("Erreur lors de la récupération des consommations pour les régions et avant la date : " + date, e);
        }
    }


    @Override
    public Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException {
        try {

            Map<Region, Consomation> tp = this.em.createQuery("SELECT r as regionSel, c as toto " +
                            " FROM Consomation c " +
                            " inner join c.region r " +
                            "WHERE c.region = :region AND c.annee <:date", Tuple.class)
                    .setParameter("region", region)
                    .setParameter("date", date)
                    .getResultStream()
                    .collect(Collectors.toMap(
                            tuple -> ((Region) tuple.get("regionSel")),
                            tuple -> ((Consomation) tuple.get("consommationMwh"))
                    ));
            return tp;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }


}