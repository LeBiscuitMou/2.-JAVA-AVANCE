package net.ent.etrs.poeleague.models.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantMetier {


    public static final int NB_MIN_CHALLENGE = 10;
    public static final int NB_MIN_REWARD_PTS = 50;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
