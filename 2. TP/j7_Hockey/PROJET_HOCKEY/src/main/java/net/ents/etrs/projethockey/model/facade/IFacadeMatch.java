package net.ents.etrs.projethockey.model.facade;

import net.ents.etrs.projethockey.model.entities.Match;

public interface IFacadeMatch {
    void saveMatch(Match match);
    void deleteMatch(Long idMatch);
}
