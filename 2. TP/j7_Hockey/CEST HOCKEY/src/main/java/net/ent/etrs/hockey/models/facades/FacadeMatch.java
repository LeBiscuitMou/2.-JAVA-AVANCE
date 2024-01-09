package net.ent.etrs.hockey.models.facades;

import net.ent.etrs.hockey.models.entities.Match;

public interface FacadeMatch {
    void saveMatch(Match match);

    void deleteMatch(Long idMatch);
}
