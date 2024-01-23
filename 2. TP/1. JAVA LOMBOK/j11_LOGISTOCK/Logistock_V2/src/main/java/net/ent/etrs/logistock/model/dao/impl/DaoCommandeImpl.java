package net.ent.etrs.logistock.model.dao.impl;

import lombok.*;
import net.ent.etrs.logistock.model.dao.IDaoCommande;
import net.ent.etrs.logistock.model.dao.base.AbstractJpaDao;
import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class DaoCommandeImpl extends AbstractJpaDao<Commande, Long> implements IDaoCommande {
    @Override
    public SortedSet<Commande> findAllCommandeByDate(LocalDate pDate) throws DaoException {
        try {
            TypedQuery<Commande> tp = this.em.createQuery(
                    "SELECT c FROM Commande c " +
                            "WHERE c.dateCommande = :date",
                    Commande.class
            );
            tp.setParameter("date", pDate);
            return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
        } catch (PersistenceException | IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws DaoException {
        try {
            TypedQuery<Commande> tp = this.em.createQuery(
                    "SELECT c FROM Commande c " +
                            "WHERE c.dateCommande BETWEEN :dateMin AND :dateMax",
                    Commande.class
            );
            tp.setParameter("dateMin", pDateMin);
            tp.setParameter("dateMax", pDateMax);
            return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
        } catch (PersistenceException | IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws DaoException {
        try {
            TypedQuery<Commande> tp = this.em.createQuery(
                    "SELECT c FROM Commande c " +
                            "JOIN c.articles a " +
                            "WHERE KEY(a) IN :articles",
                    Commande.class
            );
            tp.setParameter("articles", pArticles);
            return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) throws DaoException {
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
    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande) {
        return Optional.empty();
    }
}