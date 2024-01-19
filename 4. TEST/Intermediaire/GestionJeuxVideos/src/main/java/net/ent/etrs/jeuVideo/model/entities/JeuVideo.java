package net.ent.etrs.jeuVideo.model.entities;

import lombok.*;
import net.ent.etrs.jeuVideo.model.entities.comparator.ConsoleComparator;
import net.ent.etrs.jeuVideo.model.entities.exceptions.JeuVideoException;
import net.ent.etrs.jeuVideo.model.entities.references.ConstantesMetier;
import net.ent.etrs.jeuVideo.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "JEU_VIDEO", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__jeuVideo__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "dateSortie", "studioDev", "genre"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JeuVideo extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 3, max = 80, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 80, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date sortie doit être référencé")
    @Past(message = "la date sortie doit être dans le passé")
    //JPA
    @Column(name = "date_sortie", nullable = false, unique = false)
    private LocalDate dateSortie;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le fabriquant doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studio_dev_id", referencedColumnName = "nom", foreignKey = @ForeignKey(name = "fk__jeu_video__studio_dev__studio_dev_id"))
    private Fabriquant studioDev;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le genre doit être référencé")
    //JPA
    @Column(name = "genre", length = 40, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //BV
    @Size(min = 1, message = "JeuVideo doit avoir au moins 1 Console")
    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "jeu_video_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__console__jeu_video_id"))
    private Set<Console> plateformes = new TreeSet<>(new ConsoleComparator());

    public Set<Console> getPlateformes() {
        return Collections.unmodifiableSet(plateformes);
    }

    public void addPlateform(Console console) throws JeuVideoException {
        if(Objects.isNull(console)){
            throw new JeuVideoException(ConstantesMetier.PLATEFORME_NULL);
        }
        this.plateformes.add(console);
    }


}