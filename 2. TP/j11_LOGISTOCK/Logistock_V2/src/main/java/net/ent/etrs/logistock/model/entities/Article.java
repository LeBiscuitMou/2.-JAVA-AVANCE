package net.ent.etrs.logistock.model.entities;

import lombok.*;
import net.ent.etrs.logistock.model.entities.references.Emplacement;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "article",
        uniqueConstraints = {
                @UniqueConstraint(name = "article__designation__UK", columnNames = "designation")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"designation"}, callSuper = false)
@ToString(of = {"designation", "prix", "emplacement"}, callSuper = true)
public class Article extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la designation doit être référencé")
    @NotBlank(message = "la designation ne doit pas être vide")
    @Size(max = 255, message = "la taille du designation n'est pas valide")
    //JPA
    @Column(name = "designation", length = 255, nullable = false, unique = false)
    private String designation;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "le prix doit être positif")
    //JPA
    @Column(name = "prix", nullable = false, unique = false)
    private BigDecimal prix;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "l'emplacement doit être référencé")
    //JPA
    @Column(name = "emplacement", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Emplacement emplacement;
}