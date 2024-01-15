package net.ent.etrs.logistock.model.dao;

import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import java.io.Serializable;
import java.util.Optional;
import java.util.SortedSet;

public interface IDaoArticle extends BaseDao<Article, Serializable> {
    SortedSet<Article> findAllArticleByEmplacement(Emplacement pEmplacement) throws DaoException;

    SortedSet<Article> findAllArticleInPrix(Double pPrixMin, Double pPrixMax) throws DaoException;

    Optional<Article> findArticleById(Long pId) throws DaoException;
}
