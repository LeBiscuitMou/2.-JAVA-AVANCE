package net.ent.etrs.hockey.model.facade.impl;

import net.ent.etrs.hockey.model.dao.IDaoJoueur;
import net.ent.etrs.hockey.model.dao.impl.DaoFactory;
import net.ent.etrs.hockey.model.entities.Equipe;
import net.ent.etrs.hockey.model.entities.Joueur;
import net.ent.etrs.hockey.model.exceptions.DaoException;
import net.ent.etrs.hockey.model.facade.IFacadeJoueur;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FacadeJoueurImpl implements IFacadeJoueur {
    IDaoJoueur daoJoueur;

    public FacadeJoueurImpl() {
        daoJoueur = DaoFactory.getDaoJoueur();
    }

    @Override
    public void saveJoueur(Joueur joueur) throws BusinessException {
        try {
            daoJoueur.save(joueur);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteJoueur(Long idJoueur) throws BusinessException {
        try {
            daoJoueur.delete(idJoueur);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Joueur> findAllJoueurs() throws BusinessException {
        try {
            return Collections.unmodifiableSet(new HashSet<>(IterableUtils.toList(daoJoueur.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws BusinessException {
        try {
            return daoJoueur.findAllJoueurBirthBefore(date);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Joueur findJoueurBestScoreur() throws BusinessException {
        try {
            return daoJoueur.findJoueurBestScoreur();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}