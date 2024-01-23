package net.ent.etrs.consoElecgaz.models.entities;

import lombok.*;
import net.ent.etrs.consoElecgaz.models.entities.exceptions.RegionException;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "region", uniqueConstraints = @UniqueConstraint(name = "UK_Region_libelle", columnNames = "libelle"))
@EqualsAndHashCode(of = {"libelle", "codeRegion"}, callSuper = false)
@ToString(of = {"libelle", "codeRegion", "pointDeLivraison"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region extends AbstractEntity {
    @Setter
    @Getter
    @NotBlank
    @Length(min = 1, max = 100, message = "")
    @Column(name = "nom", nullable = false, length = 100, unique = true)
    private String libelle;

    @Getter
    @Setter
    @Positive
    @Min(0)
    @NotNull
    @Column(name = "codeRegion", nullable = false)
    private Integer codeRegion;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "le point de livraison doit être positif")
    //JPA
    @Column(name = "pointDeLivraison", nullable = false, unique = false)
    private Integer pointDeLivraison;

    //JPA
    @OneToMany()
    @JoinColumn(name = "region_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__consomation__region_id"))
    private List<Consomation> consommations = new ArrayList<>();

    public List<Consomation> getConsommations() {
        return Collections.unmodifiableList(consommations);
    }

    public void ajouterConsomation(Consomation addConsomation) throws RegionException {
        if (null == addConsomation) {
            throw new RegionException(Consomation.class.getSimpleName() + " doit être référencé");
        }
        if (this.consommations.contains(addConsomation)) {
            throw new RegionException(Consomation.class.getSimpleName() + " : existe déjà dans la liste des Consomation du Region");
        }
        this.consommations.add(addConsomation);
    }

    public void supprimerConsomation(Consomation delConsomation) throws RegionException {
        if (null == delConsomation) {
            throw new RegionException(Consomation.class.getSimpleName() + " doit être référencé");
        }
        if (!this.consommations.contains(delConsomation)) {
            throw new RegionException(Consomation.class.getSimpleName() + " : n'existe pas dans la liste des Consomation du Region");
        }
        this.consommations.remove(delConsomation);
    }
}