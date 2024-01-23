package net.ent.etrs.logistock.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.logistock.model.commons.validator.ValidException;
import net.ent.etrs.logistock.model.commons.validator.ValidatorUtils;
import net.ent.etrs.logistock.model.entities.references.Emplacement;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Article fabriquerArticle(String designation, BigDecimal prix, Emplacement emplacement) throws ValidException {
        Article article = new Article();
        article.setDesignation(designation);
        article.setPrix(prix);
        article.setEmplacement(emplacement);
        return ValidatorUtils.validate(article);
    }

    public static Commande fabriquerCommande(String numeroCommande, LocalDate dateCommande, Client client) throws ValidException {
        Commande commande = new Commande();
        commande.setNumeroCommande(numeroCommande);
        commande.setDateCommande(dateCommande);
        commande.setClient(client);
        return ValidatorUtils.validate(commande);
    }

    public static Client fabriquerClient(String nom, String prenom, String adresse, String codePostal, String ville, String numeroTelephone) throws ValidException {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(adresse);
        client.setCodePostal(codePostal);
        client.setVille(ville);
        client.setNumeroTelephone(numeroTelephone);
        return ValidatorUtils.validate(client);
    }
}
