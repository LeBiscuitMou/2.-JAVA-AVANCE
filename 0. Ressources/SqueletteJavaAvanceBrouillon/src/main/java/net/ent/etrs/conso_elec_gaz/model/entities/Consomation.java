package net.ent.etrs.conso_elec_gaz.model.entities;

import lombok.*;
import net.ent.etrs.conso_elec_gaz.model.entities.references.CategorieConsomateur;
import net.ent.etrs.conso_elec_gaz.model.entities.references.GrandSecteur;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@EqualsAndHashCode(of = {"dateAnnuelle","operateur","categorieConsomateur","secteurActivite","region","numPDL","activitePrincipale"},callSuper = false)
@ToString(of = {"numPDL","dateAnnuelle","operateur","categorieConsomateur","secteurActivite","region","activitePrincipale","consomation"},callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "consomation", uniqueConstraints = @UniqueConstraint(name = "UK__CONSOMATION_DATE_OPERATEUR_CATEGORIECONSO_SECTEUR_REGION_CONSO",columnNames = {"date_annuelle","operateur_id","categorie_consomateur","secteur_activite","region_id","numero_pdl","activite_principale_id"}))
public class Consomation extends AbstractEntity{


    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @Column(name = "numero_pdl",nullable = false)
    private String numPDL;

    /**
     * Sera toujours au premier janvier de l'année
     */
    //LBK
    @Getter @Setter
    //BV
    @Past
    @NotNull
    //JPA
    @Column(name = "date_annuelle",nullable = false)
    private LocalDate dateAnnuelle;

    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @ManyToOne
    @JoinColumn(name = "operateur_id", nullable = false)
    private Operateur operateur;
    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "categorie_consomateur", nullable = false)
    private CategorieConsomateur categorieConsomateur;

    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "secteur_activite", nullable = false)
    private GrandSecteur secteurActivite;
    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    //LBK
    @Getter @Setter
    //JPA
    @ManyToOne
    @JoinColumn(name = "activite_principale_id", nullable = true)
    private ActivitePrincipale activitePrincipale;
    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @Column(name = "consomation", nullable = false)
    private Double consomation;

}
