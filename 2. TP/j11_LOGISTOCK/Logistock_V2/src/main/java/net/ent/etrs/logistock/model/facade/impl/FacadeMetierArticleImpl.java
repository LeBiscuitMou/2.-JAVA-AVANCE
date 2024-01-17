package net.ent.etrs.logistock.model.facade.impl;

import net.ent.etrs.logistock.model.dao.IDaoArticle;
import net.ent.etrs.logistock.model.dao.impl.DaoFactory;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import net.ent.etrs.logistock.model.exceptions.DaoException;
import net.ent.etrs.logistock.model.facade.IFacadeMetierArticle;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class FacadeMetierArticleImpl implements IFacadeMetierArticle {
    private IDaoArticle daoArticle;

    public FacadeMetierArticleImpl() {
        daoArticle = DaoFactory.getDaoArticle();
    }

    @Override
    public Article createArticle(Article pArticle) throws BusinessException {
        try {
            return daoArticle.save(pArticle);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteArticle(Article pArticle) throws BusinessException {
        try {
            daoArticle.delete(pArticle);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Article> getAllArticle() throws BusinessException {
        try {
            return Collections.unmodifiableSortedSet(new TreeSet<>(IterableUtils.toList(daoArticle.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement) {
        return daoArticle.findAllArticleByEmplacement(pEmplacement);
    }

    @Override
    public SortedSet<Article> getAllArticleInPrix(Double pPrixMin, Double pPrixMax) {
        return daoArticle.findAllArticleInPrix(pPrixMin, pPrixMax);
    }

    @Override
    public Optional<Article> getArticleById(Long pId) throws BusinessException {
        try {
            return daoArticle.find(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}