package net.ent.etrs.logistock.model.facade;

import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public interface IFacadeCommande {
    // GESTION DES COMMANDES

    public Commande createCommande(Commande pCommande) throws BusinessException;

    public void deleteCommande(Commande pCommande) throws BusinessException;

    public SortedSet<Commande> getAllCommande() throws BusinessException;

    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate);

    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax);

    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles);

    public Optional<Commande> getCommandeById(Long pId);

    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande);

    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande);
}
