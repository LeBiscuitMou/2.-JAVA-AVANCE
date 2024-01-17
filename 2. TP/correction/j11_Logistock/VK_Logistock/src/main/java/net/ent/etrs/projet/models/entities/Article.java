package net.ent.etrs.projet.models.entities;

import lombok.*;
import net.ent.etrs.projet.models.entities.references.Emplacement;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "article", uniqueConstraints = @UniqueConstraint(columnNames = {"designation"}, name = "uk__article"))
@EqualsAndHashCode(of = {"designation"}, callSuper = false)
@ToString(of = {"designation", "prix", "emplacement"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends AbstractEntity implements Comparable<Article>{
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la designation doit être référencé")
    @NotBlank(message = "la designation ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du designation n'est pas valide")
    //JPA
    @Column(name = "designation", length = 255, nullable = false, unique = false)
    private String designation;
    
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la designation doit être référencé")
    //JPA
    @Column(name = "prix", nullable = false, unique = false)
    private BigDecimal prix;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "l' emplacement doit être référencé")
    //JPA
    @Column(name = "emplacement", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Emplacement emplacement;

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
    public int compareTo(Article o) {
        return this.getDesignation().compareTo(o.getDesignation());
    }
}
