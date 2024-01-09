package net.ent.etrs.hockey.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.hockey.common.utils.validator.ValidException;
import net.ent.etrs.hockey.common.utils.validator.ValidatorUtils;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static League fabriquerLeague(String nom, LocalDate dateDebut, LocalDate dateFin) throws ValidException {
        League l = new League();
        l.setNom(nom);
        l.setDateDebut(dateDebut);
        l.setDateFin(dateFin);
        ValidatorUtils.validate(l);
        return l;
    }

    public static Challenge fabriquerChallenge(String nom, String desc, Integer rewards) throws ValidException {
        Challenge c = new Challenge();
        c.setNom(nom);
        c.setDesc(desc);
        c.setRewardPoints(rewards);
        ValidatorUtils.validate(c);
        return c;
    }

    public static Personnage fabriquerPersonnage(String pseudo, LabySpecialite build, Integer level) throws ValidException {
        Personnage p = new Personnage();
        p.setPseudo(pseudo);
        p.setBuild(build);
        p.setLevel(level);
        ValidatorUtils.validate(p);
        return p;
    }
}
