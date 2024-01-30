package net.ent.etrs.gestion_jeuvideo.models.entities;

import lombok.*;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.ConsoleException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "console", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__console$"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "fabriquant"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Console extends AbstractEntity implements Comparable<Console> {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 2, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le fabriquant doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabriquant_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__console_fabriquant__fabriquant_id"))
    private Fabriquant fabriquant;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="console_pays",
          joinColumns=@JoinColumn(name = "console_id", foreignKey = @ForeignKey(name = "fk__console_pays__console_id")))
    @MapKeyColumn(name="pays_sortie", nullable = false)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name="dateSortie", nullable = false, unique = false)
    private Map<Pays, LocalDate> sorties = new HashMap<>();

    public Map<Pays, LocalDate> getSorties() {
        return Collections.unmodifiableMap(sorties);
    }

    public void setSortiesPays(Map<Pays, LocalDate> sorties) throws ConsoleException {
        if (Objects.nonNull(sorties)) {
            for(Map.Entry<Pays, LocalDate> entry : sorties.entrySet()){
                ajouterPays(entry.getKey(), entry.getValue());
            }
        }
    }

    private void ajouterPays(Pays addPays, LocalDate dateSortie) throws ConsoleException {
        if(null == addPays){
            throw new ConsoleException(Pays.class.getSimpleName() + " doit être référencé");
        }
        if(null == dateSortie){
            throw new ConsoleException("dateSortie doit être référencé");
        }
        this.sorties.put(addPays, dateSortie);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Console o) {
        return this.getNom().compareTo(o.getNom());
    }
}