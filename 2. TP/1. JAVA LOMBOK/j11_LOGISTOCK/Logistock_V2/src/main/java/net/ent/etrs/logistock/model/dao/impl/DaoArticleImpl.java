package net.ent.etrs.logistock.model.dao.impl;

import lombok.*;
import net.ent.etrs.logistock.model.dao.IDaoArticle;
import net.ent.etrs.logistock.model.dao.base.AbstractJpaDao;
import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import org.apache.commons.collections4.IterableUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class DaoArticleImpl extends AbstractJpaDao<Article, Long> implements IDaoArticle {
    @Override
    public SortedSet<Article> findAllArticleByEmplacement(Emplacement pEmplacement) {
        TypedQuery<Article> tp = this.em.createQuery(
                "SELECT a FROM Article a " +
                        "WHERE a.emplacement = :emplacement",
                Article.class
        );
        tp.setParameter("emplacement", pEmplacement);
        return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
    }

    @Override
    public SortedSet<Article> findAllArticleInPrix(Double pPrixMin, Double pPrixMax) {
        TypedQuery<Article> tp = this.em.createQuery(
                "SELECT a FROM Article a " +
                        "WHERE a.prix BETWEEN :prixMin AND :prixMax",
                Article.class
        );
        tp.setParameter("prixMin", BigDecimal.valueOf(pPrixMin));
        tp.setParameter("prixMax", BigDecimal.valueOf(pPrixMax));
        return Collections.unmodifiableSortedSet(new TreeSet<>(tp.getResultList()));
    }
}