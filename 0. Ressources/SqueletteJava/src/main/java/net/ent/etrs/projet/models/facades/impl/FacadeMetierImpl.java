package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoExemple;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Exemple;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.FacadeMetier;
import net.ent.etrs.projet.models.references.ConstMetier;
import net.ent.etrs.projet.models.commons.CoUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FacadeMetierImpl implements FacadeMetier {
    /* ******************************** VARIABLES ******************************** */
    IDaoExemple daoExemple;
    /* ******************************* CONSTRUCTOR ******************************* */
    protected FacadeMetierImpl() {
        daoExemple = DaoFactory.getDaoExemple();
    }

    /* ******************************** FONCTIONS ******************************** */

    @Override
    public void SauvegarderExemple(Exemple ajoutExemple) throws BusinessException {
        try {
            if (Objects.isNull(ajoutExemple)){
                throw new BusinessException(Exemple.class.getSimpleName() + ConstMetier.ERROR_NULL);
            }
            daoExemple.save(ajoutExemple);
        } catch (BusinessException | DaoException e) {
            throw new BusinessException(ConstMetier.ERROR_SAVE + Exemple.class.getSimpleName(), e);
        }
    }

    @Override
    public List<Exemple> selectionnerTousLesExemple() throws BusinessException {
        try {
            return Collections.unmodifiableList(CoUtils.iterableToList(daoExemple.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(ConstMetier.DATABASE_ACCESS, e);
        }
    }

    @Override
    public void modifierExemple(Exemple modifieExemple) throws BusinessException {
        try {
            if (Objects.isNull(modifieExemple)){
                throw new BusinessException(Exemple.class.getSimpleName() + ConstMetier.ERROR_NULL);
            }
            // supprime
            supprimerExemple(modifieExemple);
            SauvegarderExemple(modifieExemple);
        } catch (BusinessException e) {
            throw new BusinessException(ConstMetier.ERROR_UPDATE + Exemple.class.getSimpleName(),e);
        }
    }

    @Override
    public void supprimerExemple(Exemple supprimeExemple) throws BusinessException {
        try {
            if (Objects.isNull(supprimeExemple)){
                throw new BusinessException(Exemple.class.getSimpleName() + ConstMetier.ERROR_NULL);
            }
            daoExemple.delete(supprimeExemple.getId());
        } catch (BusinessException | DaoException e) {
            throw new BusinessException(ConstMetier.ERROR_DELETE + Exemple.class.getSimpleName(), e);
        }
    }

}
