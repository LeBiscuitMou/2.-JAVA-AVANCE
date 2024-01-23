package net.ent.etrs.poeleague.models.facades.impl;

import net.ent.etrs.poeleague.models.dao.DaoFactory;
import net.ent.etrs.poeleague.models.dao.DaoPersonnage;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.Personnage;
import net.ent.etrs.poeleague.models.facades.FacadePersonnage;
import net.ent.etrs.poeleague.models.facades.exception.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadePersonnageImpl implements FacadePersonnage {

    DaoPersonnage daoPersonnage;

    public FacadePersonnageImpl() {
        this.daoPersonnage = DaoFactory.getDaoPersonnage();
    }

    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    @Override
    public void savePersonnage(Personnage personnage) throws BusinessException {
        try {
            this.daoPersonnage.save(personnage);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de sauvegarder le personnage", e);
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
            this.daoPersonnage.delete(personnage);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de supprimer le personnage", e);
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
            return new HashSet<>(IterableUtils.toList(this.daoPersonnage.findAll()));
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les personnages", e);
        }
    }
}
