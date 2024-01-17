package net.ent.etrs.logistock.model.facade.impl;

import lombok.*;
import net.ent.etrs.logistock.model.dao.IDaoCommande;
import net.ent.etrs.logistock.model.dao.impl.DaoFactory;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.exceptions.DaoException;
import net.ent.etrs.logistock.model.facade.IFacadeMetierCommande;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

public class FacadeMetierCommandeImpl implements IFacadeMetierCommande {
    private IDaoCommande daoCommande;

    public FacadeMetierCommandeImpl() {
        daoCommande = DaoFactory.getDaoCommande();
    }

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
    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate) throws BusinessException {
        try {
            return daoCommande.findAllCommandeByDate(pDate);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws BusinessException {
        try {
            return daoCommande.getAllCommandeInDate(pDateMin, pDateMax);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws BusinessException {
        try {
            return daoCommande.getAllCommandeByAllArticle(pArticles);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Commande> getCommandeById(Long pId) throws BusinessException {
        try {
            return daoCommande.find(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande) {
        return daoCommande.getCommandeByNumeroCommande(pNumeroCommande);
    }

    @Override
    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande) {
        return daoCommande.getCommandeByNumeroCommandeWithArticles(pNumeroCommande);
    }
}