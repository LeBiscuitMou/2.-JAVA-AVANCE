package net.ent.etrs.projet.models.entities;


import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import lombok.*;
import net.ent.etrs.projet.models.exceptions.CommandeException;
import net.ent.etrs.projet.models.references.ConstMetier;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "commande", uniqueConstraints = @UniqueConstraint(columnNames = {"numeroCommande"}, name = "uk__commande"))
@EqualsAndHashCode(of = {"numeroCommande"}, callSuper = false)
@ToString(of = {"numeroCommande", "dateCommande", "client"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commande extends AbstractEntity implements Comparable<Commande>{
    //LBK
    @Getter
    //BV
    @NotNull(message = "le numéro de commande doit être référencé")
    @NotBlank(message = "le numéro de commande ne doit pas être vide")
    @Size(min = 10, max = 10, message = "la taille du numéro de commande n'est pas valide")
    //JPA
    @Column(name = "numeroCommande", length = 10, nullable = false, unique = false)
    private String numeroCommande;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date de commande doit être référencé")
    //JPA
    @Column(name = "dateCommande", nullable = false, unique = false)
    private LocalDate dateCommande;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le client doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__activitePrincipale__client_id"))
    private Client client;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="commande_article",
          joinColumns=@JoinColumn(name = "commande_id", foreignKey = @ForeignKey(name = "fk__commande_article__commande_id")))
    @MapKeyJoinColumn(name="article_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__commande_article__article_id"))
    @Column(name="quantite", nullable = false, unique = false)
    private final Map<Article, Integer> articles = new HashMap<>();

    public void setArticles(Map<Article, Integer> articles) throws CommandeException {
        if (null != articles) {
            for(Map.Entry<Article, Integer> entry : articles.entrySet()){
                ajouterArticle(entry.getKey(), entry.getValue());
            }
        }
    }

    public void setNumeroCommande(String numeroCommande, LocalDate dateCommande) throws CommandeException {
        if(null != dateCommande && null != numeroCommande){
            if(numeroCommande.charAt(4) != '-'){
                throw new CommandeException(ConstMetier.ERROR_COMMANDE_NUMERO_COMMANDE + "    1");
            }
            String[] numCo = numeroCommande.split("-");
            System.out.println(numeroCommande);
            for(String string : numCo){
                System.out.println("   - " + string);
            }

            if(numCo.length != 2){
                throw new CommandeException(ConstMetier.ERROR_COMMANDE_NUMERO_COMMANDE + "    2");
            }
            if(Integer.parseInt(numCo[0]) != dateCommande.getYear()){
                throw new CommandeException(ConstMetier.ERROR_COMMANDE_NUMERO_COMMANDE + "    3");
            }
            if(numCo[1].length() != 5){
                throw new CommandeException(ConstMetier.ERROR_COMMANDE_NUMERO_COMMANDE + "    4");
            }
            if(!isNumeric(numCo[1])){
                throw new CommandeException(ConstMetier.ERROR_COMMANDE_NUMERO_COMMANDE + "    5");
            }
        }
        this.numeroCommande = numeroCommande;
    }



    public void ajouterArticle(Article addArticle, Integer quantite) throws CommandeException {
        if(null == addArticle){
            throw new CommandeException(Article.class.getSimpleName() + " doit être référencé");
        }
        if(null == quantite){
            throw new CommandeException("quantité doit être référencé");
        }
        if(quantite < 0){
            throw new CommandeException("la quantité ne doit pas être inférieur à 0");
        }
        int quantiteAvant = 0;
        if(null != this.articles.get(addArticle)){
            quantiteAvant = this.articles.get(addArticle);
        }
        this.articles.put(addArticle, quantite + quantiteAvant);
    }
    
    public void supprimerArticle(Article delArticle) throws CommandeException {
        if(null == delArticle){
            throw new CommandeException(Article.class.getSimpleName() + " doit être référencé");
        }
        if(!this.articles.containsKey(delArticle)){
            throw new CommandeException(Article.class.getSimpleName() + " : n'existe pas dans la liste articles du Commande");
        }
        this.articles.remove(delArticle);
    }

    public Map<Article, Integer> getArticles() {
        return Collections.unmodifiableMap(articles);
    }

    public BigDecimal getPrixTotalCommande(boolean clientIsVIP){
        BigDecimal somme = BigDecimal.valueOf(0);
        for(Map.Entry<Article, Integer> entry : this.getArticles().entrySet()){
            somme = somme.add(entry.getKey().getPrix().multiply(BigDecimal.valueOf(entry.getValue())));
        }

        if(somme.compareTo(BigDecimal.valueOf(ConstMetier.PRIX_MIN_POUR_RISTOURNE)) > 0){
            somme = somme.multiply(BigDecimal.valueOf(ConstMetier.RISTOURNE_PRIX));
        }

        return clientIsVIP ? somme.multiply(BigDecimal.valueOf(ConstMetier.RISTOURNE_VIP)) : somme;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Commande o) {
        return this.getNumeroCommande().compareTo(o.numeroCommande);
    }


    private boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
