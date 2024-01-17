package net.ent.etrs.logistock.model.facade;

import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public interface IFacadeMetierCommande {
    // GESTION DES COMMANDES

    public Commande createCommande(Commande pCommande) throws BusinessException;

    public void deleteCommande(Commande pCommande) throws BusinessException;

    public SortedSet<Commande> getAllCommande() throws BusinessException;

    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate) throws BusinessException;

    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax) throws BusinessException;

    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles) throws BusinessException;

    public Optional<Commande> getCommandeById(Long pId) throws BusinessException;

    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande);

    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande);
}
