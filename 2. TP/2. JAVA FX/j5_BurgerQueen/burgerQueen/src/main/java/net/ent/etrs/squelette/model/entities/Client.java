package net.ent.etrs.squelette.model.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

//TODO faire ces 3 lignes :
// columnNames = {""} | @EqualsAndHashCode(of = {""} | @ToString(of = {""}
@Entity
@Table(name = "CLIENT", uniqueConstraints = @UniqueConstraint(columnNames = {"courriel"}, name = "uk__client__courriel"))
@EqualsAndHashCode(of = {"nom", "prenom", "courriel"}, callSuper = false)
@ToString(of = {"nom", "prenom", "courriel"}, callSuper = true)
@NoArgsConstructor
public class Client extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du nom n'est pas valide") // TODO : enlever ce qui sert à rien !!!!!
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "le nom doit être uniquement composé de lettres sans accents, ou de chiffre")
    //JPA
    @Column(name = "nom", length = 255, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le prenom doit être référencé")
    @NotBlank(message = "le prenom ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du prenom n'est pas valide") // TODO : enlever ce qui sert à rien !!!!!
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "le prenom doit être uniquement composé de lettres sans accents, ou de chiffre")
    //JPA
    @Column(name = "prenom", length = 255, nullable = false, unique = false)
    private String prenom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le courriel doit être référencé")
    @NotBlank(message = "le courriel ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du courriel n'est pas valide") // TODO : enlever ce qui sert à rien !!!!!
    @Email(message = "le courriel n'est pas valide")
    //JPA
    @Column(name = "courriel", length = 255, nullable = false, unique = true)
    private String courriel;
}