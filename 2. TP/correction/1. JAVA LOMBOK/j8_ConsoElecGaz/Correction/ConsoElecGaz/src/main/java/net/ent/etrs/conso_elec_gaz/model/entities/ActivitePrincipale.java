package net.ent.etrs.conso_elec_gaz.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(of = {"codeNAF"},callSuper = false)
@ToString(of = {"codeNAF","libelleActivite"},callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "activite_principale",uniqueConstraints = @UniqueConstraint(name = "UK__activite_principale__code_NAF",columnNames = {"code_NAF"}))
public class ActivitePrincipale extends AbstractEntity{

    @Getter
    @Setter
    @NotNull
    @Column(name = "code_NAF", nullable = false)
    private Integer codeNAF;

    @Getter @Setter
    @NotNull
    @NotBlank
    @Column(name = "libelle_activite", nullable = false)
    private String libelleActivite;





}
