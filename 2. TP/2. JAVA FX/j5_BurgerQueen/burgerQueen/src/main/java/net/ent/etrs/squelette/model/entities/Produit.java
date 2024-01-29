package net.ent.etrs.squelette.model.entities;

import lombok.*;
import net.ent.etrs.squelette.model.entities.references.TailleProduit;
import net.ent.etrs.squelette.model.entities.references.TypeProduit;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUIT", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__produit__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "prix", "typeProduit", "tailleProduit"}, callSuper = true)
@NoArgsConstructor
public class Produit extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du nom n'est pas valide") // TODO : enlever ce qui sert à rien !!!!!
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "le nom doit être uniquement composé de lettres sans accents, ou de chiffre")
    //JPA
    @Column(name = "nom", length = 255, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prix doit être référencé") //TODO enlever les trucs qui servent à rien
    @Positive(message = "le prix doit être positif")
    //JPA
    @Column(name = "prix", nullable = false, unique = false)
    private BigDecimal prix;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le type produit doit être référencé")
    //JPA
    @Column(name = "type_produit", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private TypeProduit typeProduit;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la taille produit doit être référencé")
    //JPA
    @Column(name = "taille_produit", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private TailleProduit tailleProduit;
}