package net.ent.etrs.projet.models.dao.impl;

import net.ent.etrs.projet.models.dao.IDaoArticle;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.references.Emplacement;

import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ArticleDaoJpaImpl extends JpaBaseDao<Article> implements IDaoArticle {

    /**
     * Récupère tous les articles d'un emplacement choisi, triés selon leur ordre naturel.
     *
     * @param pEmplacement l'emplacement choisi
     * @return un set d'article
     * @throws DaoException l'erreur lancée
     */
    @Override
    public SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement) throws DaoException {
        try {
            TypedQuery<Article> query = this.em.createQuery("SELECT t " +
                            "FROM Article t " +
                            "WHERE t.emplacement = :pEmplacement"
                    , Article.class);
            query.setParameter("pEmplacement", pEmplacement);


            return query.getResultStream().collect(Collectors.toCollection(TreeSet::new));
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Récupère tous les articles ayant un prix dans une certaine fourchette, triés selon leur ordre naturel.
     *
     * @param pPrixMin le prix mini
     * @param pPrixMax le prix maxi
     * @return un set d'article
     * @throws DaoException l'erreur lancée
     */
    @Override
    public SortedSet<Article> getAllArticleByEmplacement(Double pPrixMin, Double pPrixMax) throws DaoException {
        try {
            TypedQuery<Article> query = this.em.createQuery("SELECT t " +
                            "FROM Article t " +
                            "WHERE t.prix BETWEEN :prixMin AND :prixMax"
                    , Article.class);
            query.setParameter("prixMin", pPrixMin);
            query.setParameter("prixMax", pPrixMax);


            return query.getResultStream().collect(Collectors.toCollection(TreeSet::new));
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Article> findByIdentity(String designation) throws DaoException {
        try {
            TypedQuery<Article> query = this.em.createQuery("SELECT t " +
                            "FROM Article t " +
                            "WHERE t.designation = :designation"
                    , Article.class);
            query.setParameter("designation", designation);


            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult());

        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }
}
