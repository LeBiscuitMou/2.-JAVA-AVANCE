package net.ent.etrs.logistock.model.dao.impl;

import net.ent.etrs.logistock.model.dao.IDaoCommande;
import net.ent.etrs.logistock.model.dao.base.AbstractJpaDao;
import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Client;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class DaoCommandeImpl extends AbstractJpaDao<Commande, Long> implements IDaoCommande {
    @Override
    public SortedSet<Commande> findAllCommandesByDate(LocalDate pDate) throws DaoException {
        try {
            TypedQuery<Commande> tp = this.em.createQuery(
                    "SELECT c FROM Commande c " +
                            "WHERE c.dateCommande = :date", Commande.class);
            tp.setParameter("date", pDate);
            return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> findAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws DaoException {
        try {
            TypedQuery<Commande> tp = this.em.createQuery(
                    "SELECT c FROM Commande c " +
                            "WHERE c.dateCommande BETWEEN :dateMin " +
                            "AND :dateMax", Commande.class);
            tp.setParameter("dateMin", pDateMin);
            tp.setParameter("dateMax", pDateMax);
            return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> findAllCommandeByAllArticle(Set<Article> pArticles) {
        return null;
    }

    @Override
    public Optional<Commande> findCommandeById(Long pId) throws DaoException {
        try {
            TypedQuery<Commande> tp = this.em.createQuery(
                    "SELECT c FROM Commande c " +
                            "WHERE c.id = :id", Commande.class);
            tp.setParameter("id", pId);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Commande> findCommandeByNumeroCommande(String pNumeroCommande) throws DaoException {
        try {
            TypedQuery<Commande> tp = this.em.createQuery(
                    "SELECT c FROM Commande c " +
                            "WHERE c.numeroCommande = :numero", Commande.class);
            tp.setParameter("numero", pNumeroCommande);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Commande> findCommandeByNumeroCommandeWithArticles(String pNumeroCommande) {
        return Optional.empty();
    }
}