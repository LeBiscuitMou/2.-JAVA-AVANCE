package net.ent.etrs.league.model.facade.impl;

import lombok.*;
import net.ent.etrs.league.model.dao.IDaoPersonnage;
import net.ent.etrs.league.model.dao.exceptions.DaoException;
import net.ent.etrs.league.model.dao.impl.DaoFactory;
import net.ent.etrs.league.model.entities.Personnage;
import net.ent.etrs.league.model.facade.IFacadeMetierPersonnage;
import net.ent.etrs.league.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FacadeMetierPersonnageImpl implements IFacadeMetierPersonnage {
    private IDaoPersonnage daoPersonnage;

    public FacadeMetierPersonnageImpl() {
        daoPersonnage = DaoFactory.getDaoPersonnage();
    }

    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    @Override
    public void savePersonnage(Personnage personnage) throws BusinessException {
        try {
            daoPersonnage.save(personnage);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de supprimer un personnage
     *
     * @param personnage le personnage à supprimer
     */
    @Override
    public void deletePersonnage(Personnage personnage) throws BusinessException {
        try {
            daoPersonnage.delete(personnage.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de récupérer tous les personnages
     *
     * @return les personnages de la base
     */
    @Override
    public Set<Personnage> findAllPersonnages() throws BusinessException {
        try {
            return Collections.unmodifiableSet(new HashSet<>(IterableUtils.toList(daoPersonnage.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}