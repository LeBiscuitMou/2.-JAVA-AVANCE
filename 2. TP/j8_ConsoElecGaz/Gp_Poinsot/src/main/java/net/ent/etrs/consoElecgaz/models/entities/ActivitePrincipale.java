package net.ent.etrs.consoElecgaz.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

@Entity
@Table(name = "activite_principale", uniqueConstraints = @UniqueConstraint(name = "UK_activite_principale__", columnNames = ""))
@EqualsAndHashCode(of = "", callSuper = false)
@ToString(of = {}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivitePrincipale extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "le code doit être positif")
    @Min(value = 0, message = "le code ne peut pas être en dessous de 0")
    @Max(value = 9999, message = "le code ne peut pas être au dessus de 9999")
    //JPA
    @Column(name = "code", nullable = true, unique = false)
    private Integer code;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le libelle doit être référencé")
    @NotBlank(message = "le libelle ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du libelle n'est pas valide")
    //JPA
    @Column(name = "libelle", length = 255, nullable = true, unique = false)
    private String libelle;
}