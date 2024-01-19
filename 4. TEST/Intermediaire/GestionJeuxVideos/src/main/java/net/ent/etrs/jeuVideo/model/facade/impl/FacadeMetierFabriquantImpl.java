package net.ent.etrs.jeuVideo.model.facade.impl;

import net.ent.etrs.jeuVideo.model.dao.IDaoFabriquant;
import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.dao.impl.DaoFactory;
import net.ent.etrs.jeuVideo.model.entities.Fabriquant;
import net.ent.etrs.jeuVideo.model.entities.references.ConstantesMetier;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierFabriquant;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.List;

public class FacadeMetierFabriquantImpl implements IFacadeMetierFabriquant {
    private IDaoFabriquant daoFabriquant;
    public FacadeMetierFabriquantImpl() {
        daoFabriquant = DaoFactory.getDaoFabriquant();
    }

    /**
     * Permet de sauvegarder un fabriquant
     *
     * @param fabriquant le fabriquant à sauvegarder
     * @return le fabriquant sauvegarder
     */
    @Override
    public Fabriquant sauvegarderFabriquant(Fabriquant fabriquant) throws BusinessException {
        try {
            return daoFabriquant.save(fabriquant);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.ERROR_SAVE + Fabriquant.class.getSimpleName(), e);
        }
    }

    /**
     * Permet de récupérer tous les fabriquant
     *
     * @return la liste de tous les fabriquant
     */
    @Override
    public List<Fabriquant> recupererlesFabriquants() throws BusinessException {
        try {
            return Collections.unmodifiableList(IterableUtils.toList(daoFabriquant.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.ERROR_FIND + Fabriquant.class.getSimpleName(), e);
        }
    }
}