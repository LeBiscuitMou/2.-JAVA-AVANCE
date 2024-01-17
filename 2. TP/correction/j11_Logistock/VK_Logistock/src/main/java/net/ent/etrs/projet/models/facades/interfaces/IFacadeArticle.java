package net.ent.etrs.projet.models.facades.interfaces;

import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.references.Emplacement;
import net.ent.etrs.projet.models.exceptions.BusinessException;

import java.util.Optional;
import java.util.SortedSet;

public interface IFacadeArticle extends GenericFacade<Article> {
    /**
     * Ajoute un article en persistance.
     *
     * @param pArticle l'article a ajouter
     * @return l'article ajouté
     * @throws BusinessException l'erreur lancée
     */
    public Article createArticle(Article pArticle) throws BusinessException;

    /**
     * supprimer un article en persistance.
     *
     * @param pArticle l'article a supprimer
     * @throws BusinessException l'erreur lancée
     */
    public void deleteArticle(Article pArticle) throws BusinessException;

    /**
     * Récupère tous les articles triés selon leur ordre naturel.
     *
     * @return set d'article
     * @throws BusinessException l'erreur lancée
     */
    public SortedSet<Article> getAllArticle() throws BusinessException;

    /**
     * Récupère tous les articles d'un emplacement choisi, triés selon leur ordre naturel.
     *
     * @param pEmplacement l'emplacement choisi
     * @return un set d'article
     * @throws BusinessException l'erreur lancée
     */
    public SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement) throws BusinessException;

    /**
     * Récupère tous les articles ayant un prix dans une certaine fourchette, triés selon leur ordre naturel.
     *
     * @param pPrixMin le prix mini
     * @param pPrixMax le prix maxi
     * @return un set d'article
     * @throws BusinessException l'erreur lancée
     */
    public SortedSet<Article> getAllArticleInPrix(Double pPrixMin, Double pPrixMax) throws BusinessException;

    /**
     *  Récupère l'article voulu.
     *
     * @param pId l'id de l'article voulu.
     * @return un optional pouvant contenir l'article voulu
     * @throws BusinessException l'erreur lancée
     */
    public Optional<Article> getArticleById(Long pId) throws BusinessException;
}
