package net.ent.etrs.logistock.model.dao.impl;

import net.ent.etrs.logistock.model.dao.IDaoArticle;
import net.ent.etrs.logistock.model.dao.base.AbstractJpaDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class DaoArticleImpl extends AbstractJpaDao<Article, Long> implements IDaoArticle {
    @Override
    public SortedSet<Article> findAllArticleByEmplacement(Emplacement pEmplacement) throws DaoException {
        try {
            TypedQuery<Article> tp = this.em.createQuery(
                    "SELECT a FROM Article a " +
                            "WHERE a.emplacement = :emplacement", Article.class);
            tp.setParameter("emplacement", pEmplacement);
            return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Article> findAllArticleInPrix(Double pPrixMin, Double pPrixMax) throws DaoException {
        try {
            TypedQuery<Article> tp = this.em.createQuery(
                    "SELECT a FROM Article a " +
                            "WHERE a.prix BETWEEN :prixMin " +
                            "AND :prixMax", Article.class);
            tp.setParameter("prixMin", pPrixMin);
            tp.setParameter("prixMax", pPrixMax);
            return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Article> findArticleById(Long pId) throws DaoException {
        try {
            TypedQuery<Article> tp = this.em.createQuery(
                    "SELECT a FROM Article a " +
                            "WHERE a.id = :id", Article.class);
            tp.setParameter("id", pId);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}