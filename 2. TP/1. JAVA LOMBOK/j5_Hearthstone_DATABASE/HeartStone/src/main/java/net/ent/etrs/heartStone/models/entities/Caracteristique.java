package net.ent.etrs.heartStone.models.entities;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(of ="code", callSuper = false)
@ToString(of = {"code",  "description"},callSuper = true)
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Caracteristique {

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la description est null")
    @NotBlank(message = "la description est vide ")
    private String description;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le code est null")
    @NotBlank(message = "le code est vide ")
    private String code;
}
