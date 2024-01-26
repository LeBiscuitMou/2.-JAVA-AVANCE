package model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import common.utils.C;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.exceptions.BusinessException;
import model.references.TailleProduit;
import model.references.TypeProduit;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public final class EntitiesFactory {
	
	public static Commande fabriquerCommande(Client client, List<Produit> produits) throws BusinessException {
		Commande commande = new Commande();
		commande.setDateCommande(LocalDate.now());;
		commande.setClient(client);
		for (Produit abstractProduit : produits) {
			try {
				commande.ajouterProduits(abstractProduit);
			} catch (BusinessException e) {
				throw new BusinessException(C.MSG_COMMANDE_CONSTRUCTION_IMPOSSIBLE);
			}
		}
		return commande;
	}
	
	public static Client fabriquerClient(String nom,String prenom,String courriel) {
		Client c = new Client();
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setCourriel(courriel);
		return c;
	}
	
	public static Produit fabriquerProduit(String nom,TailleProduit tailleProduit, BigDecimal prix, TypeProduit typeProduit) {
		Produit p = new Produit();
		p.setNom(nom);
		p.setTailleProduit(tailleProduit);
		p.setPrix(prix);
		p.setTypeProduit(typeProduit);
		return p;
	}
	
	
}
