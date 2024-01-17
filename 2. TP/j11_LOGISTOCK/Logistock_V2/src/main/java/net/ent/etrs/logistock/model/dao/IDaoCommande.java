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
    SortedSet<Commande> findAllCommandeByDate(LocalDate pDate) throws DaoException;

    SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws DaoException;

    SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws DaoException;

    Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) throws DaoException;

    Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande);
}
