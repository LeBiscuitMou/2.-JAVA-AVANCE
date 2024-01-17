package net.ent.etrs.projet.models.dao;

import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.Commande;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public interface IDaoCommande extends BaseDao<Commande> {

    /**
     * Récupère toutes les commandes ayant eu lieu à la date choisie.
     *
     * @param pDate la date choisie
     * @return un set trié de commandes
     * @throws DaoException l'exception lancée
     */
    SortedSet<Commande> getAllCommandeByDate(LocalDate pDate) throws DaoException;

    /**
     * Récupère toutes les commandes dont la date est dans une fourchette donnée.
     *
     * @param pDateMin borne inférieure
     * @param pDateMax borne supérieure
     * @return un set trié de commandes
     * @throws DaoException l'exception lancée
     */
    SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws DaoException;

    /**
     * Récupère toutes les commandes ayant tous les articles de la liste d'article donnée
     *
     * @param pArticles une liste d'article
     * @return un set trié de commandes
     * @throws DaoException l'exception lancée
     */
    SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws DaoException;


    Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) throws DaoException;

    /**
     * Récupère une commande selon son numéro de commande, seulement si elle au moins 1 article.
     *
     * @param pNumeroCommande le numéro de la commande
     * @return une commande
     * @throws DaoException l'exception lancée
     */
    Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande) throws DaoException;

}
