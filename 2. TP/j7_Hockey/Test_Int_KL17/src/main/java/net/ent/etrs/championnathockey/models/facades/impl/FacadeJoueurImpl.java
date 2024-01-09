package net.ent.etrs.championnathockey.models.facades.impl;

import net.ent.etrs.championnathockey.models.daos.DaoFactory;
import net.ent.etrs.championnathockey.models.daos.DaoJoueur;
import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.facades.FacadeJoueur;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FacadeJoueurImpl implements FacadeJoueur {

    DaoJoueur daoJoueur;

    public FacadeJoueurImpl() {
        this.daoJoueur = DaoFactory.getDaoJoueur();
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
            return new HashSet<>(IterableUtils.toList(daoJoueur.findAll()));
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
            return daoJoueur.findJoueurBestScoreur().orElseThrow();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
