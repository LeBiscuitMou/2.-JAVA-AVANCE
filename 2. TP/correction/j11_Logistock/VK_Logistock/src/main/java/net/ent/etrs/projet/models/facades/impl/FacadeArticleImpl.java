package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.commons.CoUtils;
import net.ent.etrs.projet.models.dao.IDaoArticle;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.references.Emplacement;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeArticle;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class FacadeArticleImpl extends GenericFacadeImpl<Article> implements IFacadeArticle {
    protected IDaoArticle daoArticle;

    protected FacadeArticleImpl() {
        super(DaoFactory.getDaoArticle());
        daoArticle = DaoFactory.getDaoArticle();
    }


    /**
     * Ajoute un article en persistance.
     *
     * @param pArticle l'article a ajouter
     * @return l'article ajouté
     * @throws BusinessException l'erreur lancée
     */
    @Override
    public Article createArticle(Article pArticle) throws BusinessException {
        return super.save(pArticle);
    }

    /**
     * supprimer un article en persistance.
     *
     * @param pArticle l'article a supprimer
     * @throws BusinessException l'erreur lancée
     */
    @Override
    public void deleteArticle(Article pArticle) throws BusinessException {
        super.delete(pArticle.getId());
    }

    /**
     * Récupère tous les articles triés selon leur ordre naturel.
     *
     * @return set d'article
     * @throws BusinessException l'erreur lancée
     */
    @Override
    public SortedSet<Article> getAllArticle() throws BusinessException {
        return new TreeSet<>(CoUtils.iterableToSet(super.findAll()));
    }

    /**
     * Récupère tous les articles d'un emplacement choisi, triés selon leur ordre naturel.
     *
     * @param pEmplacement l'emplacement choisi
     * @return un set d'article
     * @throws BusinessException l'erreur lancée
     */
    @Override
    public SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement) throws BusinessException {
        try {
            return daoArticle.getAllArticleByEmplacement(pEmplacement);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Récupère tous les articles ayant un prix dans une certaine fourchette, triés selon leur ordre naturel.
     *
     * @param pPrixMin le prix mini
     * @param pPrixMax le prix maxi
     * @return un set d'article
     * @throws BusinessException l'erreur lancée
     */
    @Override
    public SortedSet<Article> getAllArticleInPrix(Double pPrixMin, Double pPrixMax) throws BusinessException {
        try {
            return daoArticle.getAllArticleByEmplacement(pPrixMin, pPrixMax);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Récupère l'article voulu.
     *
     * @param pId l'id de l'article voulu.
     * @return un optional pouvant contenir l'article voulu
     * @throws BusinessException l'erreur lancée
     */
    @Override
    public Optional<Article> getArticleById(Long pId) throws BusinessException {
        return super.find(pId);
    }
}
