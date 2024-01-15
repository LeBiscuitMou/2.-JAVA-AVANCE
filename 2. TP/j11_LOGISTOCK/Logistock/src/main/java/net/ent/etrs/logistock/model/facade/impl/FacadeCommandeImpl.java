package net.ent.etrs.logistock.model.facade.impl;

import net.ent.etrs.logistock.model.dao.DaoFactory;
import net.ent.etrs.logistock.model.dao.IDaoCommande;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.exceptions.DaoException;
import net.ent.etrs.logistock.model.facade.IFacadeCommande;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.time.LocalDate;
import java.util.*;

public class FacadeCommandeImpl implements IFacadeCommande {
    private final IDaoCommande daoCommande = DaoFactory.getDaoCommande();
    @Override
    public Commande createCommande(Commande pCommande) throws BusinessException {
        try {
            return daoCommande.save(pCommande);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteCommande(Commande pCommande) throws BusinessException {
        try {
            daoCommande.delete(pCommande.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> getAllCommande() throws BusinessException {
        try {
            return Collections.unmodifiableSortedSet(new TreeSet<>(IterableUtils.toList(daoCommande.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate) {
        return daoCommande.findAllCommandesByDate(pDate);
    }

    @Override
    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) {
        return daoCommande.findAllCommandeInDate(pDateMin, pDateMax);
    }

    @Override
    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) {
        return daoCommande.findAllCommandeByAllArticle(pArticles);
    }

    @Override
    public Optional<Commande> getCommandeById(Long pId) {
        return daoCommande.findCommandeById(pId);
    }

    @Override
    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) {
        return daoCommande.findCommandeByNumeroCommande(pNumeroCommande);
    }

    @Override
    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande) {
        return daoCommande.findCommandeByNumeroCommandeWithArticles(pNumeroCommande);
    }
}