package net.ent.etrs.logistock.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "commande",
        uniqueConstraints = {
                @UniqueConstraint(name = "commande__numero_commande__UK", columnNames = "numero_commande")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"numeroCommande"}, callSuper = false)
@ToString(of = {"numeroCommande", "dateCommande", "client"}, callSuper = true)
public class Commande extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le numero commande doit être référencé")
    @NotBlank(message = "le numero commande ne doit pas être vide")
    @Size(min = 1, max = 10, message = "la taille du numero commande n'est pas valide")
    @Pattern(regexp = "\\b\\d{4}-(?:0\\d{4}|[1-9]\\d{0,4})\\b")
    //JPA
    @Column(name = "numeroCommande", length = 10, nullable = false, unique = false)
    private String numeroCommande;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date commande doit être référencé")
    @PastOrPresent(message = "la date commande ne doit pas être dans le futur")
    //JPA
    @Column(name = "dateCommande", nullable = false, unique = false)
    private LocalDate dateCommande;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le client doit être référencé")
    //JPA
    @JoinColumn(name = "client", nullable = false, unique = false)
    @OneToOne
    private Client client;

    @ElementCollection()
    @CollectionTable(name="commande_article",
          joinColumns=@JoinColumn(name = "commande_id", foreignKey = @ForeignKey(name = "fk__commande_article__commande_id")))
    @MapKeyJoinColumn(name="article_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__commande_article__article_id"))
    @Column(name="quantité", nullable = false, unique = false)
    private Map<Article, Integer> articles = new HashMap<>();

    public Map<Article, Integer> getarticles() {
        return Collections.unmodifiableMap(this.articles);
    }

    public BigDecimal calculerPrixTotal() {
        BigDecimal result = BigDecimal.ZERO;

        for(Map.Entry<Article, Integer> articlesEntry : this.articles.entrySet()){
            result = result.add(articlesEntry.getKey().getPrix().multiply(BigDecimal.valueOf(articlesEntry.getValue())));
        }

        if (result.compareTo(BigDecimal.valueOf(1000)) > 0) {
            result = result.multiply(BigDecimal.valueOf(0.95));
        }

        return result;
    }
}