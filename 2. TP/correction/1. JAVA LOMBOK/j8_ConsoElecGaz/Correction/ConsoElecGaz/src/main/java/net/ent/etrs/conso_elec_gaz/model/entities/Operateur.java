package net.ent.etrs.conso_elec_gaz.model.entities;

import lombok.*;
import net.ent.etrs.conso_elec_gaz.model.entities.references.Filiere;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(of = {"nom","filiere"},callSuper = false)
@ToString(of = {"nom","filiere"},callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "operateur",uniqueConstraints = @UniqueConstraint(name = "UK__operateur_nom_filiere",columnNames = {"nom","filiere"}))
public class Operateur extends AbstractEntity{

    @Getter @Setter

    @NotNull
    @NotBlank
    @Column(name = "nom", nullable = false)
    private String nom;

    @Getter @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "filiere", nullable = false)
    private Filiere filiere;
}
