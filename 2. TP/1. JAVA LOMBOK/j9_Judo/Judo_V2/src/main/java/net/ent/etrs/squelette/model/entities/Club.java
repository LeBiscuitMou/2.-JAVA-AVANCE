package net.ent.etrs.squelette.model.entities;

import lombok.*;
import net.ent.etrs.squelette.model.entities.references.Ville;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "club", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__club__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "ville"}, callSuper = true)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

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

    //BV
    @Size(min = 1, message = "Club doit avoir au moins 1 Judoka")
    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__judoka__club_id"))
    private List<Judoka> membres = new ArrayList<>();

    public List<Judoka> getMembres() {
        return Collections.unmodifiableList(membres);
    }
}