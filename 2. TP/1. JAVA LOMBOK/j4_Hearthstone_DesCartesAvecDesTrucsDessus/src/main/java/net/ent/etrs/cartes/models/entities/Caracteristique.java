package net.ent.etrs.cartes.models.entities;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@EqualsAndHashCode(of = "code")
@ToString(of = {"code", "desc"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Caracteristique extends AbstractEntity {
    //LBK
    @NonNull
    //BV
    @NotNull(message = "Le code ne doit pas être null")
    @NotBlank(message = "Le code ne peut pas être vide")
    private String code;

    //LBK
    @NonNull
    //BV
    @NotNull(message = "La desc ne doit pas être null")
    @NotBlank(message = "La desc ne peut pas être vide")
    private String desc;
}