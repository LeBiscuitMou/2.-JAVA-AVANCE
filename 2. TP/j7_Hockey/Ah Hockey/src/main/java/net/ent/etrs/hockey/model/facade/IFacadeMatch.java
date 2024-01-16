package net.ent.etrs.hockey.model.facade;

import net.ent.etrs.hockey.model.entities.Match;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;

public interface IFacadeMatch {
    void saveMatch(Match match) throws BusinessException;
    void deleteMatch(Long idMatch) throws BusinessException;
}
