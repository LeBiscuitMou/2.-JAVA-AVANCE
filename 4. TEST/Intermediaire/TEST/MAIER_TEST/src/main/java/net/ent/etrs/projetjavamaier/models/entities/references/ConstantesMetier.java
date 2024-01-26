package net.ent.etrs.projetjavamaier.models.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantesMetier {
    public static final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String CONSOLE_NULL = "err la console ne peit pas être null";
    public static final String ATTENTION_OBJET_NULL = "attention objets null";
    public static final String ERR_JEUX_VIDEO_CONSOLE_MIN = "err il doit y avoir au moins 2 jeux vidéo pour une console";
}
