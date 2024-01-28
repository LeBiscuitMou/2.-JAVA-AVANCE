package net.ent.etrs.keepFit.model.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRESTATION")
@EqualsAndHashCode(of = {"libelle"}, callSuper = false)
@ToString(of = {"libelle", "cout"}, callSuper = true)
@NoArgsConstructor
public class Prestation extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le libelle doit être référencé")
    @NotBlank(message = "le libelle ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du libelle n'est pas valide")
    //JPA
    @Column(name = "libelle", length = 255, nullable = false, unique = false)
    private String libelle;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "le cout doit être positif")
    //JPA
    @Column(name = "cout", nullable = false, unique = false)
    private BigDecimal cout;
}