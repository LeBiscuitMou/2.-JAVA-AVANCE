package net.ent.etrs.logistock.model.facades;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public interface Facade {

    // GESTION DES ARTICLES
    
    public Article createArticle(Article pArticle);
    
    public void deleteArticle(Article pArticle);
    
    public SortedSet<Article> getAllArticle();
    
    public SortedSet<Article> getAllArticleByEmplacement(Emplacement pEmplacement);
    
    public SortedSet<Article> getAllArticleInPrix(Double pPrixMin, Double pPrixMax);
    
    public Optional<Article> getArticleById(Long pId);
    
    // GESTION DES CLIENTS
    
    public Client createClient(Client pClient);
    
    public void deleteClient(Client pClient);
    
    public SortedSet<Client> getAllClient();
    
    public Optional<Client> getClientById(Long pId);
    
    // GESTION DES COMMANDES
    
    public Commande createCommande(Commande pCommande);
    
    public void deleteCommande(Commande pCommande);
    
    public SortedSet<Commande> getAllCommande();
    
    public SortedSet<Commande> getAllCommandeByDate(LocalDate pDate);
    
    public SortedSet<Commande> getAllCommandeInDate(LocalDate pDateMin, LocalDate pDateMax);
    
    public SortedSet<Commande> getAllCommandeByAllArticle(Set<Article> pArticles);
    
    public Optional<Commande> getCommandeById(Long pId);
    
    public Optional<Commande> getCommandeByNumeroCommande(String pNumeroCommande);
    
    public Optional<Commande> getCommandeByNumeroCommandeWithArticles(String pNumeroCommande);

}
