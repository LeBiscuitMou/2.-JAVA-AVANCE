package net.ent.etrs.projet.models.facades;

import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Club;
import net.ent.etrs.projet.models.entities.Competition;
import net.ent.etrs.projet.models.entities.Judoka;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.references.Categorie;
import net.ent.etrs.projet.models.references.Grade;
import net.ent.etrs.projet.models.references.Ville;

import java.util.List;
import java.util.Optional;

public interface FacadeMetier {

    Club saveClub(final Club pClub) throws BusinessException;
    
    Optional<Club> findClub(final Long pId) throws BusinessException;
    
    List<Club> findAllClub() throws BusinessException;
    
    void deleteClub(final Long pId) throws BusinessException;
    
    Optional<Club> findClubByNomWithJudokas(final String pName) throws BusinessException;
    
    List<Club> findAllClubByVille(final Ville pVille) throws BusinessException;
    
    Judoka saveJudoka(final Judoka pJudoka) throws BusinessException;
    
    Optional<Judoka> findJudoka(final Long pId) throws BusinessException;
    
    List<Judoka> findAllJudoka() throws BusinessException;
    
    void deleteJudoka(final Long pId) throws BusinessException;
    
    List<Judoka> findAllJudokaByNom(final String pNom) throws BusinessException;
    
    List<Judoka> findAllByGrade(final Grade pGrade) throws BusinessException;
    
    List<Judoka> findAllByCategorie(final Categorie pCategorie) throws DaoException, BusinessException;
    
    Optional<Judoka> findJudokaByPalmares(final Competition pCompetition, final Integer pClassement) throws BusinessException;
    
    List<Judoka> findAllJudokaByCompetition(final Competition pCompetition);
    
    Competition saveCompetition(final Competition pCompetition) throws BusinessException;
    
    Optional<Competition> findCompetition(final Long pId) throws BusinessException;
    
    List<Competition> findAllCompetition() throws BusinessException;
    
    void deleteCompetition(final Long pId) throws BusinessException;
    
    List<Competition> findAllCompetitionByNom(final String pNom) throws BusinessException;
    
    List<Competition> findAllCompetitionByAnnee(final int pAnnee) throws BusinessException;
    
    List<Competition> findAllCompetitionByVille(final Ville pVille);
    
    List<Competition> findAllCompetitionInAnnees(final int anneeDebut, final int anneeFin);

}
