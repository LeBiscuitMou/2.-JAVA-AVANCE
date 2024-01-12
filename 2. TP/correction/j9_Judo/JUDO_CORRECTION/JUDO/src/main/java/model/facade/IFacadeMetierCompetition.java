package model.facade;

import model.entities.Competition;
import model.entities.references.Ville;
import model.facade.exceptions.CompetitionException;

import java.util.List;
import java.util.Optional;

public interface IFacadeMetierCompetition {
    
    Competition saveCompetition(final Competition pCompetition) throws CompetitionException;
    
    Optional<Competition> findCompetition(final Long pId) throws CompetitionException;
    
    List<Competition> findAllCompetition() throws CompetitionException;
    
    void deleteCompetition(final Long pId) throws CompetitionException;
    
    List<Competition> findAllCompetitionByNom(final String pNom) throws CompetitionException;
    
    List<Competition> findAllCompetitionByAnnee(final int pAnnee) throws CompetitionException;
    
    List<Competition> findAllCompetitionByVille(final Ville pVille) throws CompetitionException;
    
    List<Competition> findAllCompetitionInAnnees(final int anneeDebut, final int anneeFin) throws CompetitionException;
    
}
