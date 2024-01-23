package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.commons.CoUtils;
import net.ent.etrs.projet.models.dao.IDaoCommande;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.Article;
import net.ent.etrs.projet.models.entities.Commande;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeCommande;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class FacadeCommandeImpl extends GenericFacadeImpl<Commande> implements IFacadeCommande {
    protected IDaoCommande daoCommande;

    protected FacadeCommandeImpl() {
        super(DaoFactory.getDaoCommande());
        daoCommande = DaoFactory.getDaoCommande();
    }


    @Override
    public Commande createCommande(Commande pCommande) throws BusinessException {
        return super.save(pCommande);
    }

    @Override
    public void deleteCommande(Commande pCommande) throws BusinessException {
        delete(pCommande.getId());
    }

    @Override
    public SortedSet<Commande> getAllCommande() throws BusinessException {
        return new TreeSet<>(CoUtils.iterableToSet(super.findAll()));
    }

    /**
     * Récupère toutes les commandes ayant eu lieu à la date choisie.
     *
     * @param pDate la date choisie
     * @return un set trié de commandes
     * @throws BusinessException l'exception lancée
     */
    @Override
    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate) throws BusinessException {
        try {
            return daoCommande.getAllCommandeByDate(pDate);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Récupère toutes les commandes dont la date est dans une fourchette donnée.
     *
     * @param pDateMin borne inférieure
     * @param pDateMax borne supérieure
     * @return un set trié de commandes
     * @throws BusinessException l'exception lancée
     */
    @Override
    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws BusinessException {
        try {
            return daoCommande.getAllCommandeInDate(pDateMin, pDateMax);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Récupère toutes les commandes ayant tous les articles de la liste d'article donnée
     *
     * @param pArticles une liste d'article
     * @return un set trié de commandes
     * @throws BusinessException l'exception lancée
     */
    @Override
    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws BusinessException {
        try {
            return daoCommande.getAllCommandeByAllArticle(pArticles);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Optional<Commande> getCommandeById(Long pId) throws BusinessException {
        return super.find(pId);
    }

    @Override
    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) throws BusinessException {
        try {
            return daoCommande.getCommandeByNumeroCommande(pNumeroCommande);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Récupère une commande selon son numéro de commande, seulement si elle au moins 1 article.
     *
     * @param pNumeroCommande le numéro de la commande
     * @return une commande
     * @throws BusinessException l'exception lancée
     */
    @Override
    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande) throws BusinessException {
        try {
            return daoCommande.getCommandeByNumeroCommandeWithArticles(pNumeroCommande);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
