package net.ent.etrs.projet.models.dao;

import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.references.Emplacement;
import net.ent.etrs.projet.models.exceptions.BusinessException;

import java.util.Optional;
import java.util.SortedSet;

public interface IDaoArticle extends BaseDao<Article> {
    /**
     * Récupère tous les articles d'un emplacement choisi, triés selon leur ordre naturel.
     *
     * @param pEmplacement l'emplacement choisi
     * @return un set d'article
     * @throws DaoException l'erreur lancée
     */
    SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement) throws DaoException;

    /**
     * Récupère tous les articles ayant un prix dans une certaine fourchette, triés selon leur ordre naturel.
     *
     * @param pPrixMin le prix mini
     * @param pPrixMax le prix maxi
     * @return un set d'article
     * @throws DaoException l'erreur lancée
     */
    SortedSet<Article> getAllArticleByEmplacement(Double pPrixMin, Double pPrixMax) throws DaoException;

    Optional<Article> findByIdentity(String designation) throws DaoException;
}
