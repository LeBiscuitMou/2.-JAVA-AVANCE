package net.ent.etrs.gestion_jeuvideo.models.entities;

import lombok.*;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.JeuVideoException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "jeu_video", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__jeu_video$"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "dateSortie", "studioDev", "genre"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JeuVideo extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 3, max = 80, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 80, nullable = false, unique = true)
    private String nom;


    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la date sortie doit être référencé")
    @Past(message = "la date sortie doit être dans le passé")
    //JPA
    @Column(name = "date_sortie", nullable = false, unique = false)
    private LocalDate dateSortie;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le studio de développement doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studioDev_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__jeuVideo_fabriquant__studioDev_id"))
    private Fabriquant studioDev;
    
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le genre doit être référencé")
    //JPA
    @Column(name = "genre", length = 40, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    
    //JPA
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "jeuVideo_console",
          joinColumns = @JoinColumn(name = "jeuVideo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__jeuVideo_console__jeuVideo_id")),
          inverseJoinColumns = @JoinColumn(name = "console_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__jeuVideo_console__console_id")))
    private final Set<Console> plateformes = new HashSet<>();
    
    public void ajouterConsole(Console addConsole) throws JeuVideoException {
        if(null == addConsole){
            throw new JeuVideoException(Console.class.getSimpleName() + " doit être référencé");
        }
        if(this.plateformes.contains(addConsole)){
            throw new JeuVideoException(Console.class.getSimpleName() + " : existe déjà dans la liste des Console du JeuVideo");
        }
        this.plateformes.add(addConsole);
    }

    public Set<Console> getPlateformes() {
        return Collections.unmodifiableSet(plateformes);
    }
}