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
                @UniqueConstraint(name = "commande__numeroCommande__UK", columnNames = "numeroCommande")
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
    @NotNull(message = "le numero de commande doit être référencé")
    @NotBlank(message = "le numero de commande ne doit pas être vide")
    @Size(min = 1, max = 10, message = "la taille du numero de commande n'est pas valide")
    @Pattern(regexp = "^\\d{4}-(?:0\\d{4}|[1-9]\\d{4}|99999)$\n")
    //JPA
    @Column(name = "numeroCommande", length = 10, nullable = false, unique = false)
    private String numeroCommande;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date de commande doit être référencé")
    @PastOrPresent(message = "la date de commande ne doit pas être dans le futur")
    @Past(message = "la date de commande doit être dans le passé")
    @Future(message = "la date de commande doit être dans le futur")
    @FutureOrPresent(message = "la date de commande ne doit pas être dans le passé")
    //JPA
    @Column(name = "dateCommande", nullable = false, unique = false)
    private LocalDate dateCommande;

    //LBK
    @Getter
    //BV
    @NotNull(message = "le client doit être référencé")
    //JPA
    @JoinColumn(name = "client_id", nullable = false, unique = false)
    @ManyToOne
    private Client client;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="commande_article",
          joinColumns=@JoinColumn(name = "commande_id", foreignKey = @ForeignKey(name = "fk__commande_article__commande_id")))
    @MapKeyJoinColumn(name="article_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__commande_article__article_id"))
    @Column(name="quantite", nullable = false, unique = false)
    private Map<Article, Integer> articles = new HashMap<>();

    public Map<Article, Integer> getArticles() {
        return Collections.unmodifiableMap(articles);
    }

    public BigDecimal calculerPrixTotal() {
        BigDecimal result = new BigDecimal(0);
        
        for(Map.Entry<Article, Integer> articleEntry : articles.entrySet()){
            result = result.add(articleEntry.getKey().getPrix().multiply(BigDecimal.valueOf(articleEntry.getValue())));
        }

        if (result.compareTo(BigDecimal.valueOf(1000)) > 0) {
            result = result.multiply(BigDecimal.valueOf(0.95));
        }
        
        return result;
    }
}