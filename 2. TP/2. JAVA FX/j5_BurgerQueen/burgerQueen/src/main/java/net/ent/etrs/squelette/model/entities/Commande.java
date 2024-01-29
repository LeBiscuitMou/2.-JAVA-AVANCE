package net.ent.etrs.squelette.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.*;
import net.ent.etrs.squelette.model.exceptions.CommandeException;

import javax.persistence.*;
import javax.validation.constraints.*;

//TODO faire ces 3 lignes :
// columnNames = {""} | @EqualsAndHashCode(of = {""} | @ToString(of = {""}
@Entity
@Table(name = "COMMANDE")
@EqualsAndHashCode(of = {"client"}, callSuper = false)
@ToString(of = {"dateCommande", "client"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commande extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la date commande doit être référencé")
    @PastOrPresent(message = "la date commande ne doit pas être dans le futur")  //TODO enlever les trucs qui servent à rien
    //JPA
    @Column(name = "date_commande", nullable = false, unique = false)
    private LocalDate dateCommande;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="commande_produit",
          joinColumns=@JoinColumn(name = "commande_id", foreignKey = @ForeignKey(name = "fk__commande_produit__commande_id")))
    @MapKeyJoinColumn(name="produit_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__commande_produit__produit_id"))
    @Column(name="integer", nullable = false, unique = false)
    private Map<Produit, Integer> produits = new HashMap<>();

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le client doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__commande_client__client_id"))
    private Client client;

    public Map<Produit, Integer> getProduits() {
        return Collections.unmodifiableMap(produits);
    }

    public void ajouterProduit(Produit addProduit, Integer integer) throws CommandeException {
        Integer oldValue = 0;
        if(null == addProduit){
            throw new CommandeException(Produit.class.getSimpleName() + " doit être référencé");
        }
        if(null == integer){
            throw new CommandeException("integer doit être référencé");
        }
        if(this.produits.containsKey(addProduit)){
            //TODO vérifier si il faut addition ou remplacer
            oldValue = this.produits.get(addProduit);
        }
        this.produits.put(addProduit, integer + oldValue);
    }

    public void retirerProduit(Produit delProduit) throws CommandeException {
        if(null == delProduit){
            throw new CommandeException(Produit.class.getSimpleName() + " doit être référencé");
        }
        if(!this.produits.containsKey(delProduit)){
            throw new CommandeException(Produit.class.getSimpleName() + " : n'existe pas dans la liste produits du Commande");
        }
        this.produits.remove(delProduit);
    }

    public BigDecimal totalCommande() {
        BigDecimal total = BigDecimal.ZERO;
        for(Map.Entry<Produit, Integer> mapEntry : produits.entrySet()){
            total = total.add(mapEntry.getKey().getPrix().multiply(BigDecimal.valueOf(mapEntry.getValue())));
        }
        return total;
    }
}