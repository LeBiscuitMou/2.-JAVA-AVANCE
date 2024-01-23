package net.ent.etrs.projet.models.dao.impl;

import net.ent.etrs.projet.models.dao.IDaoCommande;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.Commande;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CommandeDaoJpaImpl extends JpaBaseDao<Commande> implements IDaoCommande {

    /**
     * Récupère toutes les commandes ayant eu lieu à la date choisie.
     *
     * @param pDate la date choisie
     * @return un set trié de commandes
     * @throws DaoException l'exception lancée
     */
    @Override
    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate) throws DaoException {
        try {
            TypedQuery<Commande> query = this.em.createQuery("SELECT t " +
                            "FROM Commande t " +
                            "WHERE t.dateCommande = :pDate"
                    , Commande.class);
            query.setParameter("pDate", pDate);


            return query.getResultStream().collect(Collectors.toCollection(TreeSet::new));
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Récupère toutes les commandes dont la date est dans une fourchette donnée.
     *
     * @param pDateMin borne inférieure
     * @param pDateMax borne supérieure
     * @return un set trié de commandes
     * @throws DaoException l'exception lancée
     */
    @Override
    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws DaoException {
        try {
            TypedQuery<Commande> query = this.em.createQuery("SELECT t " +
                            "FROM Commande t " +
                            "WHERE t.dateCommande BETWEEN :dateMin AND :dateMax"
                    , Commande.class);
            query.setParameter("dateMin", pDateMin);
            query.setParameter("dateMax", pDateMax);

            return query.getResultStream().collect(Collectors.toCollection(TreeSet::new));
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Récupère toutes les commandes ayant tous les articles de la liste d'article donnée
     *
     * @param pArticles une liste d'article
     * @return un set trié de commandes
     * @throws DaoException l'exception lancée
     */
    @Override
    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws DaoException {
        try {
            String sql = """
                SELECT c FROM Commande c 
                WHERE 1=1 """;

            for(Article art : pArticles){
                sql += " AND (:a"+art.getId() + ") = KEY(c.articles) ";
            }

            TypedQuery<Commande> q = this.em.createQuery(sql, Commande.class);

            for(Article a : pArticles){
                q.setParameter("a"+a.getId().toString(), a);
            }

            return new TreeSet<>(q.getResultList());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) throws DaoException {
        try {
            TypedQuery<Commande> query = this.em.createQuery("SELECT t " +
                            "FROM Commande t " +
                            "WHERE t.numeroCommande = :pNumeroCommande"
                    , Commande.class);
            query.setParameter("pNumeroCommande", pNumeroCommande);
            
            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Récupère une commande selon son numéro de commande, seulement si elle au moins 1 article.
     *
     * @param pNumeroCommande le numéro de la commande
     * @return une commande
     * @throws DaoException l'exception lancée
     */
    @Override
    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande) throws DaoException {
        try {
            TypedQuery<Commande> query = this.em.createQuery("""
                            SELECT t
                            FROM Commande t INNER JOIN t.articles a
                            WHERE t.numeroCommande = :pNumeroCommande"""
                    , Commande.class);
            query.setParameter("pNumeroCommande", pNumeroCommande);
            
            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }
}
