package net.ent.etrs.judo.model.facades;

import net.ent.etrs.judo.model.entities.Club;
import net.ent.etrs.judo.model.entities.Competition;
import net.ent.etrs.judo.model.entities.Judoka;
import net.ent.etrs.judo.model.entities.references.Categorie;
import net.ent.etrs.judo.model.entities.references.Grade;
import net.ent.etrs.judo.model.entities.references.Ville;

import java.util.List;
import java.util.Optional;

public interface IFacade {

    Club saveClub(final Club pClub);
    
    Optional<Club> findClub(final Long pId);
    
    List<Club> findAllClub();
    
    void deleteClub(final Long pId);
    
    Optional<Club> findClubByNomWithJudokas(final String pName);
    
    List<Club> findAllClubByVille(final Ville pVille);
    
    Judoka saveJudoka(final Judoka pJudoka);
    
    Optional<Judoka> findJudoka(final Long pId);
    
    List<Judoka> findAllJudoka();
    
    void deleteJudoka(final Long pId);
    
    List<Judoka> findAllJudokaByNom(final String pNom);
    
    List<Judoka> findAllByGrade(final Grade pGrade);
    
    List<Judoka> findAllByCategorie(final Categorie pCategorie);
    
    Optional<Judoka> findJudokaByPalmares(final Competition pCompetition, final Integer pClassement);
    
    List<Judoka> findAllJudokaByCompetition(final Competition pCompetition);
    
    Competition saveCompetition(final Competition pCompetition);
    
    Optional<Competition> findCompetition(final Long pId);
    
    List<Competition> findAllCompetition();
    
    void deleteCompetition(final Long pId);
    
    List<Competition> findAllCompetitionByNom(final String pNom);
    
    List<Competition> findAllCompetitionByAnnee(final int pAnnee);
    
    List<Competition> findAllCompetitionByVille(final Ville pVille);
    
    List<Competition> findAllCompetitionInAnnees(final int anneeDebut, final int anneeFin);

}
