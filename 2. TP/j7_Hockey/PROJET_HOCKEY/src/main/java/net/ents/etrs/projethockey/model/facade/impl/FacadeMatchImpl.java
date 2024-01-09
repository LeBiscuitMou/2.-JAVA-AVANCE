package net.ents.etrs.projethockey.model.facade.impl;

import net.ents.etrs.projethockey.model.dao.IDaoMatch;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Joueur;
import net.ents.etrs.projethockey.model.entities.Match;
import net.ents.etrs.projethockey.model.facade.IFacadeMatch;

import java.util.Optional;

public class FacadeMatchImpl implements IFacadeMatch {

    IDaoMatch iDaoMatch;
    @Override
    public void saveMatch(Match match) {
        try {
            iDaoMatch.save(match);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMatch(Long idMatch) {
        try {
            Optional<Match> match = iDaoMatch.find(idMatch);

            if(match.isPresent()){
                iDaoMatch.delete(match.get());
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
