package net.ent.etrs.conso_elec_gaz.model.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(of = {"codeRegion"},callSuper = false)
@ToString(of = {"codeRegion","libelleRegion"},callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "region", uniqueConstraints = @UniqueConstraint(name = "UK__REGION_CODE",columnNames = {"code_region"}))
public class Region extends AbstractEntity{

    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @Column(name = "code_region",nullable = false)
    private Integer codeRegion;

    @Getter @Setter
    //BV
    @NotNull
    @NotBlank
    //JPA
    @Column(name = "libelle_region",nullable = false)
    private String libelleRegion;


}
