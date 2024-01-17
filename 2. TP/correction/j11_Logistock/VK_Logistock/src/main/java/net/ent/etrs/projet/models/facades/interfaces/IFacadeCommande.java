package net.ent.etrs.projet.models.facades.interfaces;

import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.Commande;
import net.ent.etrs.projet.models.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public interface IFacadeCommande extends GenericFacade<Commande> {
    public Commande createCommande(Commande pCommande) throws BusinessException;

    public void deleteCommande(Commande pCommande) throws BusinessException;

    public SortedSet<Commande> getAllCommande() throws BusinessException;

    /**
     * Récupère toutes les commandes ayant eu lieu à la date choisie.
     *
     * @param pDate la date choisie
     * @return un set trié de commandes
     * @throws BusinessException l'exception lancée
     */
    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate) throws BusinessException;

    /**
     * Récupère toutes les commandes dont la date est dans une fourchette donnée.
     *
     * @param pDateMin borne inférieure
     * @param pDateMax borne supérieure
     * @return un set trié de commandes
     * @throws BusinessException l'exception lancée
     */
    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws BusinessException;

    /**
     * Récupère toutes les commandes ayant tous les articles de la liste d'article donnée
     *
     * @param pArticles une liste d'article
     * @return un set trié de commandes
     * @throws BusinessException l'exception lancée
     */
    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws BusinessException;

    public Optional<Commande> getCommandeById(Long pId) throws BusinessException;

    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) throws BusinessException;

    /**
     * Récupère une commande selon son numéro de commande, seulement si elle au moins 1 article.
     *
     * @param pNumeroCommande le numéro de la commande
     * @return une commande
     * @throws BusinessException l'exception lancée
     */
    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande) throws BusinessException;
}
