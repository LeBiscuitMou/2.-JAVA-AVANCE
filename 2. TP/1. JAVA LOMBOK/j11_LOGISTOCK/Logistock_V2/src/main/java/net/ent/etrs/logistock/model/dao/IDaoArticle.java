package net.ent.etrs.logistock.model.dao;

import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.references.Emplacement;

import java.io.Serializable;
import java.util.SortedSet;

public interface IDaoArticle extends BaseDao<Article, Serializable> {
    SortedSet<Article> findAllArticleByEmplacement(Emplacement pEmplacement);

    SortedSet<Article> findAllArticleInPrix(Double pPrixMin, Double pPrixMax);
}
