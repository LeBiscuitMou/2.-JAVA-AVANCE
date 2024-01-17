package net.ent.etrs.projet.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.entities.references.Emplacement;
import net.ent.etrs.projet.models.exceptions.ClientException;
import net.ent.etrs.projet.models.exceptions.CommandeException;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.commons.ValidatorUtils;
import net.ent.etrs.projet.models.references.ConstMetier;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Article fabriquerArticle(String designation, BigDecimal prix, Emplacement emplacement) throws EntitiesFactoryException {
        Article article = new Article();
        article.setDesignation(designation);
        article.setPrix(prix);
        article.setEmplacement(emplacement);

        try {
            ValidatorUtils.refactorException(article);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return article;
    }
    
    public static Commande fabriquerCommande(String numeroCommande, LocalDate dateCommande, Client client, Map<Article, Integer> lesArticles) throws EntitiesFactoryException {
        StringBuilder errorMsg = new StringBuilder();

        Commande commande = new Commande();
        commande.setDateCommande(dateCommande);
        try {
            commande.setNumeroCommande(numeroCommande, dateCommande);
        } catch (CommandeException e) {
            errorMsg.append(e.getMessage());
        }
        commande.setClient(client);
        try {
            commande.setArticles(lesArticles);
        } catch (CommandeException e) {
            errorMsg.append(e.getMessage());
        }
    
        try {
            ValidatorUtils.refactorException(commande, errorMsg.toString());
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return commande;
    }
    
    public static Client fabriquerClient(String nom, String prenom, String adresse, String codePostal, String ville, String numeroTelephone) throws EntitiesFactoryException {
        StringBuilder errorMsg = new StringBuilder();

        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(adresse);
        client.setCodePostal(codePostal);
        client.setVille(ville);
        try {
            client.setNumeroTelephone(numeroTelephone);
        } catch (ClientException e) {
            errorMsg.append(e.getMessage());
        }


        try {
            ValidatorUtils.refactorException(client, errorMsg.toString());
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }
    
        return client;
    }






}



