package net.ent.etrs.projet.models.entities;

import lombok.*;
import net.ent.etrs.projet.models.exceptions.ClubException;
import net.ent.etrs.projet.models.references.Ville;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "club", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "prenom"}, name = "uk__judoka__nom_prenom"))
@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"ville", "nom"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "la ville doit être référencé")
    //JPA
    @Column(name = "ville", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Ville ville;
    
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = false)
    private String nom;

    //JPA
    @OneToMany()
    @JoinColumn(name = "club_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__judoka__club_id"))
    private final List<Judoka> membres = new ArrayList<>();

    public List<Judoka> getMembres() {
        return Collections.unmodifiableList(membres);
    }
}