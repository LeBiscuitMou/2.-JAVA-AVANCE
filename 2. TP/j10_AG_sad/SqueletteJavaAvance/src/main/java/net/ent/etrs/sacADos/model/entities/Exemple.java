package net.ent.etrs.sacADos.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "exemple",
        uniqueConstraints = {
                @UniqueConstraint(name = "exemple____UK", columnNames = "")
        }
)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {""}, callSuper = false)
@ToString(of = {""}, callSuper = true)
public class Exemple extends AbstractEntity {
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
}