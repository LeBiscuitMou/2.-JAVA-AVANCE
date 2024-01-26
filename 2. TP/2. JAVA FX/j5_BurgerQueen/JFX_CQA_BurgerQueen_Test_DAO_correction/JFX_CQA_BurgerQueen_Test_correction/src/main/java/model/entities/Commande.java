package model.entities;

import common.utils.C;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.exceptions.BusinessException;
import model.exceptions.ProduitException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"client", "produits"})
@NoArgsConstructor
//@RequiredArgsConstructor

@Entity
@Table(name = "COMMANDE", uniqueConstraints = @UniqueConstraint(name = "COMMANDE__CLIENT__DATE_COMMANDE", columnNames = {"CLIENT_ID", "DATE_COMMANDE"}))
//@NamedQueries(
//		@NamedQuery(name = "COMMANDE_BY_CLIENT", query = REQ.COMMANDE_PAR_CLIENT)
//		)
public class Commande extends AbstractEntity {


    @Getter
    @Setter
    @NotNull(message = C.MSG_CLIENT_NON_NULL)
    @OneToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false, foreignKey = @ForeignKey(name = "COMMANDE__CLIENT_FK"))
    Client client;

    @Getter
    @Setter
    @PastOrPresent(message = C.MSG_COMMANDE_DATE_NON_VALIDE)
    @NotNull(message = C.MSG_COMMANDE_DATE_NULL)
    @Column(name = "DATE_COMMANDE")
    LocalDateTime dateCommande = LocalDateTime.now();

    @NotNull(message = C.MSG_MAP_PRODUIT_NON_NULL)
    @Size(min = 1, message = C.MSG_LISTE_PRODUIT_VIDE)
    @ElementCollection
    @CollectionTable(name = "COMMANDE_PRODUIT", joinColumns = @JoinColumn(name = "COMMANDE_ID", foreignKey = @ForeignKey(name = "COMMANDE__COMMANDE_ID__FK")))
    @MapKeyJoinColumn(name = "PRODUIT_ID", foreignKey = @ForeignKey(name = "COMMANDE__PRODUIT_ID__FK"))
    @Column(name = "PRODUITS")
    Map<Produit, Integer> produits = new HashMap<>();


    public Map<Produit, Integer> getProduits() {
        return Collections.unmodifiableMap(this.produits);
    }

    public void ajouterProduits(Produit produit) throws ProduitException {
        if (Objects.isNull(produit)) {
            throw new ProduitException(C.MSG_AJOUTER_PRODUIT_EXCEPTION);
        }
//		if(this.produits.containsKey(p)) {
//			this.produits.put(p, this.produits.get(p)+1);
//		}
//		else {
//			this.produits.put(p, 1);
//		}
        this.produits.computeIfPresent(produit, (k, v) -> v + 1);
        this.produits.computeIfAbsent(produit, v -> new Integer(1));
    }

    public void retirerProduit(Produit produit) throws BusinessException {
        if (Objects.isNull(produit)) {
            throw new BusinessException(C.MSG_RETIRER_PRODUIT_EXCEPTION);
        }
        if (produit != null) {
            this.produits.computeIfPresent(produit, (k, v) -> majQteProduit(k, v));

        }
    }

    private Integer majQteProduit(Produit produit, Integer quantité) {
        if (quantité <= 1) {
            this.produits.remove(produit);
        }
        return quantité - 1;
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
