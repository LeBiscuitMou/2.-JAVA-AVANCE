package net.ent.etrs.logistock.model.facade;

import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.references.Emplacement;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;

import java.util.Optional;
import java.util.SortedSet;

public interface IFacadeArticle {
    // GESTION DES ARTICLES

    public Article createArticle(Article pArticle) throws BusinessException;

    public void deleteArticle(Article pArticle) throws BusinessException;

    public SortedSet<Article> getAllArticle() throws BusinessException;

    public SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement) throws BusinessException;

    public SortedSet<Article> getAllArticleInPrix(Double pPrixMin, Double pPrixMax) throws BusinessException;

    public Optional<Article> getArticleById(Long pId) throws BusinessException;
}
