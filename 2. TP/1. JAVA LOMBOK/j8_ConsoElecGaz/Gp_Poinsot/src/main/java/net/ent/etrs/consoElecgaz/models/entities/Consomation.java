package net.ent.etrs.consoElecgaz.models.entities;

import lombok.*;
import net.ent.etrs.consoElecgaz.models.entities.references.CodeConso;
import net.ent.etrs.consoElecgaz.models.entities.references.GrandSecteur;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "consomation", uniqueConstraints = @UniqueConstraint(name = "UK_consomation_nom", columnNames = "nom"))
@EqualsAndHashCode(of = "", callSuper = false)
@ToString(of = {}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Consomation extends AbstractEntity {


    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la region doit etre referencee")
    //JPA
    @Column(name = "region", nullable = false, unique = false)
    @ManyToOne
    private Region region;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "la consommation Mwh doit être positif")
    @Min(value = 0, message = "la consommation Mwh ne peut pas être en dessous de 0")
    @Max(value = 99999, message = "la consommation Mwh ne peut pas être au dessus de 99999")
    //JPA
    @Column(name = "consommationMwh", nullable = false, unique = false)
    private float consommationMwh;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le code conso doit être référencé")
    //JPA
    @Column(name = "codeConso", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private CodeConso codeConso;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "l'annee doit etre referencee")
    //JPA
    @Column(name = "annee", nullable = false, unique = false)
    private LocalDate annee;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le grand secteur doit etre referencee")
    //JPA
    @Column(name = "secteur", nullable = false, unique = false)
    private GrandSecteur secteur;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "l'operateur doit etre referencee")
    //JPA
    @Column(name = "operateur", nullable = false, unique = false)
    @ManyToOne
    private Operateur operateur;
}