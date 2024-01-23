package net.ent.etrs.conso_elec_gaz.model.daos.impl;

import net.ent.etrs.conso_elec_gaz.model.daos.DaoConsomation;
import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.*;
import net.ent.etrs.conso_elec_gaz.model.entities.references.Filiere;
import net.ent.etrs.conso_elec_gaz.model.entities.references.GrandSecteur;

import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoConsomationImplJPA extends AbstractJpaDao<Consomation> implements DaoConsomation {
    @Override
    public List<Consomation> consomationsBySecteur(GrandSecteur secteur) throws DaoException {
        try {
            TypedQuery<Consomation> tp = this.em.createQuery(
                    "SELECT c FROM Consomation c " +
                            " WHERE c.secteurActivite = :secteur", Consomation.class);
            tp.setParameter("secteur", secteur);
            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Consomation> consomationsBeforeDate(LocalDate date) throws DaoException {
        try {
            TypedQuery<Consomation> tp = this.em.createQuery(
                    "SELECT c FROM Consomation c " +
                            " WHERE c.dateAnnuelle <= :date", Consomation.class);
            tp.setParameter("date", date);
            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Consomation> consomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws DaoException {
        try {
            TypedQuery<Consomation> tp = this.em.createQuery(
                    "SELECT c FROM Consomation c " +
                            " WHERE c.dateAnnuelle <= :date" +
                            " AND c.region IN :listRegion", Consomation.class);
            tp.setParameter("date", date);
            tp.setParameter("listRegion", lesRegions);
            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws DaoException {
        try {
            TypedQuery<Consomation> tp = this.em.createQuery(
                    "SELECT c FROM Consomation c " +
                            " WHERE c.dateAnnuelle >= :date" +
                            " AND c.operateur = :operateur", Consomation.class);
            tp.setParameter("date", date);
            tp.setParameter("operateur", operateur);
            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws DaoException {
        try {
            Map<LocalDate, Double> tp = this.em.createQuery(
                            "SELECT c.dateAnnuelle as date,sum(c.consomation) as sommeConso " +
                                    "FROM Consomation c " +
                                    "WHERE c.dateAnnuelle <= :date AND c.region = :region " +
                                    "GROUP BY c.dateAnnuelle", Tuple.class)
                    .setParameter("date", date).setParameter("region", region)
                    .getResultStream()
                    .collect(Collectors.toMap(
                            tuple -> ((LocalDate) tuple.get("date")),
                            tuple -> ((Double) tuple.get("sommeConso"))
                    ));
            return tp;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Map<Filiere, Map<LocalDate, Double>> sumConsomationsByFiliereForRegionAndBeforeDate(Region region, LocalDate date) throws DaoException {
        try {
            Map<Filiere, Map<LocalDate,Double>> retour = new HashMap<>();
            for (Filiere f : Filiere.values()) {
                Map<LocalDate, Double> tp = this.em.createQuery(
                                "SELECT c.dateAnnuelle as date,sum(c.consomation) as sommeConso " +
                                        "FROM Consomation c " +
                                        "WHERE c.dateAnnuelle <= :date " +
                                        "AND c.region = :region " +
                                        "AND c.operateur.filiere = :filiere " +
                                        "GROUP BY c.dateAnnuelle", Tuple.class)
                        .setParameter("date", date).setParameter("region", region).setParameter("filiere", f)
                        .getResultStream()
                        .collect(Collectors.toMap(
                                tuple -> ((LocalDate) tuple.get("date")),
                                tuple -> ((Double) tuple.get("sommeConso"))
                        ));
                retour.put(f, tp);
            }
            return retour;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
