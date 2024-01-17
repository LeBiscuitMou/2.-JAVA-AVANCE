package net.ent.etrs.projet.models.entities;


import lombok.*;
import net.ent.etrs.projet.models.exceptions.ClientException;
import net.ent.etrs.projet.models.references.ConstMetier;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "client", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "prenom"}, name = "uk__client"))
@EqualsAndHashCode(of = {"nom", "prenom"}, callSuper = false)
@ToString(of = {"nom", "prenom", "adresse", "codePostal", "ville", "numeroTelephone"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client extends AbstractEntity implements Comparable<Client>{
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prénom doit être référencé")
    @NotBlank(message = "le prénom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du prénom n'est pas valide")
    //JPA
    @Column(name = "prenom", length = 50, nullable = false, unique = false)
    private String prenom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "l' adresse doit être référencé")
    @NotBlank(message = "l' adresse ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du adresse n'est pas valide")
    //JPA
    @Column(name = "adresse", length = 255, nullable = false, unique = false)
    private String adresse;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le code postal doit être référencé")
    @NotBlank(message = "le code postal ne doit pas être vide")
    @Size(min = 1, max = 5, message = "la taille du code postal n'est pas valide")
    //JPA
    @Column(name = "codePostal", length = 5, nullable = false, unique = false)
    private String codePostal;
    
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la ville doit être référencé")
    @NotBlank(message = "la ville ne doit pas être vide")
    @Size(min = 1, max = 100, message = "la taille du ville n'est pas valide")
    //JPA
    @Column(name = "ville", length = 100, nullable = false, unique = false)
    private String ville;
    
    //LBK
    @Getter
    //BV
    @NotNull(message = "le numéro de téléphone doit être référencé")
    @NotBlank(message = "le numéro de téléphone ne doit pas être vide")
    @Size(min = 1, max = 10, message = "la taille du numéro de téléphone n'est pas valide")
    //JPA
    @Column(name = "numeroTelephone", length = 10, nullable = true, unique = false)
    private String numeroTelephone;

    public void setNumeroTelephone(String numeroTelephone) throws ClientException {
        if(null != numeroTelephone){
            if (!numeroTelephone.startsWith("0")){
                throw new ClientException("le numéro de téléphone du client doit commencer par un 0");
            }
            this.numeroTelephone = numeroTelephone;
        }else {
            this.numeroTelephone = null;
        }
    }

    public boolean isVIP(Set<Commande> lesCommandes){
        BigDecimal somme = BigDecimal.valueOf(0);
        for(Commande commande : lesCommandes){
            if(commande.getClient().equals(this)){
                if (commande.getDateCommande().isAfter(LocalDate.now().minusMonths(6))){
                    for(Map.Entry<Article, Integer> entry : commande.getArticles().entrySet()){
                        somme = somme.add(entry.getKey().getPrix().multiply(BigDecimal.valueOf(entry.getValue())));
                    }
                }
            }
        }
        return somme.doubleValue() >= ConstMetier.VIP_REQUIREMENT;
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
    public int compareTo(Client o) {
        int difference = this.getNom().compareTo(o.getNom());
        if(difference != 0){
            return difference;
        }
        return this.getPrenom().compareTo(o.getPrenom());
    }
}
