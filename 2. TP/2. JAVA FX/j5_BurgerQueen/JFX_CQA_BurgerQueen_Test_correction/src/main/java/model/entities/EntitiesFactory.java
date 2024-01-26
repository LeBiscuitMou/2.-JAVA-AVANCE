package model.entities;

import common.validator.ValidException;
import common.validator.ValidatorUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.exceptions.ProduitException;
import model.references.TailleProduit;
import model.references.TypeProduit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Commande fabriquerCommande(Client client, List<Produit> produits) throws ProduitException, ValidException {
        Commande commande = new Commande();
        commande.setDateCommande(LocalDateTime.now());

        commande.setClient(client);
        for (Produit abstractProduit : produits) {

            commande.ajouterProduits(abstractProduit);

        }
        return ValidatorUtils.validate(commande);
    }

    public static Client fabriquerClient(String nom, String prenom, String courriel) throws ValidException {
        Client c = new Client();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setCourriel(courriel);
        return ValidatorUtils.validate(c);
    }

    public static Produit fabriquerProduit(String nom, TailleProduit tailleProduit, BigDecimal prix, TypeProduit typeProduit) throws ValidException {
        Produit p = new Produit();
        p.setNom(nom);
        p.setTailleProduit(tailleProduit);
        p.setPrix(prix);
        p.setTypeProduit(typeProduit);
        return ValidatorUtils.validate(p);
    }


}
