package net.ent.etrs.heartStone.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "caracteristique", uniqueConstraints = @UniqueConstraint(name = "uk__caracteristique_code", columnNames = {"code"}))
@EqualsAndHashCode(of ="code", callSuper = false)
@ToString(of = {"code",  "description"},callSuper = true)
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Caracteristique extends AbstractEntity {

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la description est null")
    @NotBlank(message = "la description est vide ")
    @Size(max = 255, message = "la description ne peut pas contenir plus de 255 caractères")
    //JPA
    @Column(name = "description", nullable = false, length = 255)
    private String description;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le code est null")
    @NotBlank(message = "le code est vide ")
    @Column(name = "code", nullable = false, unique = true)
    private String code;
}
