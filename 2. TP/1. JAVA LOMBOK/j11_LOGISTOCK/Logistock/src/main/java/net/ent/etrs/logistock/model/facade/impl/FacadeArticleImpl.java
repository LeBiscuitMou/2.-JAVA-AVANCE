package net.ent.etrs.logistock.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.logistock.model.dao.DaoFactory;
import net.ent.etrs.logistock.model.dao.IDaoArticle;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import net.ent.etrs.logistock.model.exceptions.DaoException;
import net.ent.etrs.logistock.model.facade.IFacadeArticle;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FacadeArticleImpl implements IFacadeArticle {
    private final IDaoArticle daoArticle = DaoFactory.getDaoArticle();
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
            daoArticle.delete(pArticle.getId());
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
    public SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement) throws BusinessException {
        try {
            return daoArticle.findAllArticleByEmplacement(pEmplacement);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Article> getAllArticleInPrix(Double pPrixMin, Double pPrixMax) throws BusinessException {
        try {
            return daoArticle.findAllArticleInPrix(pPrixMin, pPrixMax);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Article> getArticleById(Long pId) throws BusinessException {
        try {
            return daoArticle.findArticleById(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}