package net.ent.etrs.consoElecgaz.models.entities;

import lombok.*;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "operateur", uniqueConstraints = @UniqueConstraint(name = "UK_operateur_nom", columnNames = "nom"))
@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom", "annee", "filiere"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Operateur extends AbstractEntity {

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom est null")
    @NotBlank(message = "le nom est vide")
    @Size(max = 200, min = 5)
    //JPA
    @Column(name = "nom", length = 200, nullable = false, unique = true)
    private String nom;


    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la filière ne peux pas être null")
    @Column(name = "filiere", nullable = false)
    private Filiere filiere;
}