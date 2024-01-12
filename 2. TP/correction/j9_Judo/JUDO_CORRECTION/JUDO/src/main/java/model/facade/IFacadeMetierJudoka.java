package model.facade;

import model.entities.Competition;
import model.entities.Judoka;
import model.entities.references.Categorie;
import model.entities.references.Grade;
import model.facade.exceptions.JudokaException;

import java.util.List;
import java.util.Optional;

public interface IFacadeMetierJudoka {
    
    Judoka saveJudoka(final Judoka pJudoka) throws JudokaException;
    
    Optional<Judoka> findJudoka(final Long pId) throws JudokaException;
    
    List<Judoka> findAllJudoka() throws JudokaException;
    
    void deleteJudoka(final Long pId) throws JudokaException;
    
    List<Judoka> findAllJudokaByNom(final String pNom) throws JudokaException;
    
    List<Judoka> findAllByGrade(final Grade pGrade) throws JudokaException;
    
    List<Judoka> findAllByCategorie(final Categorie pCategorie) throws JudokaException;
    
    Optional<Judoka> findJudokaByPalmares(final Competition pCompetition, final Integer pClassement) throws JudokaException;
    
    List<Judoka> findAllJudokaByCompetition(final Competition pCompetition) throws JudokaException;
    
}
