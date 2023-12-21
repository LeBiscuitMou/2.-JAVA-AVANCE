package net.ent.etrs.leagueJPA.models.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.leagueJPA.models.commons.validator.ValidException;
import net.ent.etrs.leagueJPA.models.commons.validator.ValidatorUtils;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Personnage fabriquerPersonnage(String pseudo, LabySpecialite build, Integer number) throws ValidException {
        Personnage personnage = new Personnage();
        personnage.setPseudo(pseudo);
        personnage.setBuild(build);
        personnage.setLevel(number);
        return ValidatorUtils.validate(personnage);
    }

    public static Challenge fabriquerChallenge(String nom, String desc, Integer rewards) throws ValidException {
        Challenge challenge = new Challenge();
        challenge.setNom(nom);
        challenge.setDesc(desc);
        challenge.setRewardPoints(rewards);
        return ValidatorUtils.validate(challenge);
    }

    public static League fabriquerLeague(String nom, LocalDate dateDebut, LocalDate dateFin) throws ValidException {
        League league = new League();
        league.setNom(nom);
        league.setDateDebut(dateDebut);
        league.setDateFin(dateFin);
        return ValidatorUtils.validate(league);
    }
}
