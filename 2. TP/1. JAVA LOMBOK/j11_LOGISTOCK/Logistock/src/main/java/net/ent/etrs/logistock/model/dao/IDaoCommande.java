package net.ent.etrs.logistock.model.dao;

import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public interface IDaoCommande extends BaseDao<Commande, Serializable> {
    SortedSet<Commande> findAllCommandesByDate(LocalDate pDate) throws DaoException;

    SortedSet<Commande> findAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws DaoException;

    SortedSet<Commande> findAllCommandeByAllArticle(Set<Article> pArticles);

    Optional<Commande> findCommandeById(Long pId) throws DaoException;

    Optional<Commande> findCommandeByNumeroCommande(String pNumeroCommande) throws DaoException;

    Optional<Commande> findCommandeByNumeroCommandeWithArticles(String pNumeroCommande);
}
