package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.DaoClub;
import net.ent.etrs.projet.models.dao.DaoCompetition;
import net.ent.etrs.projet.models.dao.DaoJudoka;
import net.ent.etrs.projet.models.dao.DaoFactory;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Club;
import net.ent.etrs.projet.models.entities.Competition;
import net.ent.etrs.projet.models.entities.Judoka;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.FacadeMetier;
import net.ent.etrs.projet.models.references.Categorie;
import net.ent.etrs.projet.models.references.Grade;
import net.ent.etrs.projet.models.references.Ville;
import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FacadeMetierImpl implements FacadeMetier {
    DaoJudoka daoJudoka;
    DaoClub daoClub;
    DaoCompetition daoCompetition;

    public FacadeMetierImpl() {
        this.daoClub = DaoFactory.getDaoClub();
        this.daoCompetition = DaoFactory.getDaoCompetition();
        this.daoJudoka = DaoFactory.getDaoJudoka();
    }

    @Override
    public Club saveClub(Club pClub) throws BusinessException {
        try {
            return daoClub.save(pClub);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Club> findClub(Long pId) throws BusinessException {
        try {
            return this.daoClub.getClub(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Club> findAllClub() throws BusinessException {
        try {
            return new ArrayList<>(IterableUtils.toList(daoClub.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteClub(Long pId) throws BusinessException {
        try {
            daoClub.delete(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Club> findClubByNomWithJudokas(String pName) throws BusinessException {
        try {
            return daoClub.findClubByNomWithJudokas(pName);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Club> findAllClubByVille(Ville pVille) throws BusinessException {
        try {
            return daoClub.findAllClubByVille(pVille);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Judoka saveJudoka(Judoka pJudoka) throws BusinessException {
        try {
            return daoJudoka.save(pJudoka);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Judoka> findJudoka(Long pId) throws BusinessException {
        try {
            return this.daoJudoka.getJudoka(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> findAllJudoka() throws BusinessException {
        try {
            return new ArrayList<>(IterableUtils.toList(daoJudoka.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteJudoka(Long pId) throws BusinessException {
        try {
            daoJudoka.delete(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> findAllJudokaByNom(String pNom) throws BusinessException {
        try {
            return daoJudoka.getAllJudokaByNom(pNom);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> findAllByGrade(Grade pGrade) throws BusinessException {
        try {
            return daoJudoka.getAllByGrade(pGrade);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> findAllByCategorie(Categorie pCategorie) throws BusinessException {
        try {
            return daoJudoka.getAllByCategorie(pCategorie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Judoka> findJudokaByPalmares(Competition pCompetition, Integer pClassement) throws BusinessException {
        try {
            return daoJudoka.getJudokaByPalmares(pCompetition, pClassement);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> findAllJudokaByCompetition(Competition pCompetition) {
        return daoJudoka.getAllJudokaByCompetition(pCompetition);
    }

    @Override
    public Competition saveCompetition(Competition pCompetition) throws BusinessException {
        try {
            return daoCompetition.save(pCompetition);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Competition> findCompetition(Long pId) throws BusinessException {
        try {
            return daoCompetition.getCompetition(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> findAllCompetition() throws BusinessException {
        try {
            return Collections.unmodifiableList(IterableUtils.toList(daoCompetition.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteCompetition(Long pId) throws BusinessException {
        try {
            daoCompetition.delete(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> findAllCompetitionByNom(String pNom) throws BusinessException {
        try {
            return daoCompetition.getAllCompetitionByNom(pNom);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> findAllCompetitionByAnnee(int pAnnee) throws BusinessException {
        try {
            return daoCompetition.getAllCompetitionByAnnee(pAnnee);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> findAllCompetitionByVille(Ville pVille) {
        return daoCompetition.getAllCompetitionByVille(pVille);
    }

    @Override
    public List<Competition> findAllCompetitionInAnnees(int anneeDebut, int anneeFin) {
        return daoCompetition.getAllCompetitionInAnnees(anneeDebut, anneeFin);
    }
}