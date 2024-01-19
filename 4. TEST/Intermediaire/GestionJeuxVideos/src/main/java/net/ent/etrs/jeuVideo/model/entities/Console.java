package net.ent.etrs.jeuVideo.model.entities;

import lombok.*;
import net.ent.etrs.jeuVideo.model.entities.exceptions.ConsoleException;
import net.ent.etrs.jeuVideo.model.entities.references.ConstantesMetier;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "CONSOLE", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__console__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "fabriquant"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Console extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 2, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le fabriquant doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabriquant_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__console_fabriquant__fabriquant_id"))
    private Fabriquant fabriquant;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="console_pays",
          joinColumns=@JoinColumn(name = "console_id", foreignKey = @ForeignKey(name = "fk__console_pays__console_id")))
    @MapKeyJoinColumn(name="pays_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__console_pays__pays_id"))
    @Column(name="date_sortie", nullable = false, unique = false)
    private Map<Pays, LocalDate> sorties = new HashMap<>();

    public Map<Pays, LocalDate> getSorties() {
        return Collections.unmodifiableMap(sorties);
    }

    public void setSortiesPays(Pays pays, LocalDate dateSortie) throws ConsoleException {
        if(Objects.isNull(pays)){
            throw new ConsoleException(ConstantesMetier.PAYS_NULL);
        }
        if(Objects.isNull(dateSortie)){
            throw new ConsoleException(ConstantesMetier.DATE_SORTIE_NULL);
        }
        this.sorties.put(pays, dateSortie);
    }
}