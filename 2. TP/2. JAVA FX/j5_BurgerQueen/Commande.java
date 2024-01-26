package model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import common.utils.C;
import common.utils.REQ;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import model.exceptions.BusinessException;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of= {"client", "produits"})
@NoArgsConstructor
//@RequiredArgsConstructor

@Entity
@Table(name = "COMMANDE")
@NamedQueries(
		@NamedQuery(name = "COMMANDE_BY_CLIENT", query = REQ.COMMANDE_PAR_CLIENT)
		)
public class Commande extends AbstractEntity{

	
	@Getter
	@Setter
	@NotNull(message = C.MSG_CLIENT_NON_NULL)
	@NonNull
	Client client;
	
	@Getter
	@Setter
	@PastOrPresent
	LocalDate dateCommande = LocalDate.now();
	
	@NotNull(message = C.MSG_MAP_PRODUIT_NON_NULL)
	@Size(min = 1, message = C.MSG_LISTE_PRODUIT_VIDE)
	Map<Produit, Integer> produits = new HashMap<Produit, Integer>();
	

	public Map<Produit, Integer> getProduits(){
		return Collections.unmodifiableMap(this.produits);
	}
	
	public void ajouterProduits(Produit produit) throws BusinessException {
		if(Objects.isNull(produit)) {
			throw new BusinessException(C.MSG_AJOUTER_PRODUIT_EXCEPTION);
		}
//		if(this.produits.containsKey(p)) {
//			this.produits.put(p, this.produits.get(p)+1);
//		}
//		else {
//			this.produits.put(p, 1);
//		}
		this.produits.computeIfPresent(produit, (k,v)-> v+1);
		this.produits.computeIfAbsent(produit, v -> new Integer(1));
	}
	
	public void retirerProduit(Produit produit) throws BusinessException {
		if(Objects.isNull(produit)) {
			throw new BusinessException(C.MSG_RETIRER_PRODUIT_EXCEPTION);
		}
		if(produit != null) {
			this.produits.computeIfPresent(produit, (k,v)-> majQteProduit(k,v));
			
		}
	}
	
	private Integer majQteProduit(Produit produit, Integer quantité) {
		if(quantité<=1) {
			this.produits.remove(produit);
		}
		return quantité-1;
	}
	

	public BigDecimal totalCommande() {
		BigDecimal totalCommande = new BigDecimal(0);
		
//		for (int i = 0; i < this.listeDeProduits.size(); i++) {
//			totalConso = totalConso.add(listeDeProduits.get(i).getPrix());
//		}
		
		//equivalent à la boucle for 
		for (Produit varProduit : this.produits.keySet()) {
			totalCommande = totalCommande.add(varProduit.getPrix().multiply(new BigDecimal(produits.get(varProduit))));
		}

		return totalCommande;
	}
	
}
