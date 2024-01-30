package net.ent.etrs.gestion_jeuvideo.models.facades.impl;

import net.ent.etrs.gestion_jeuvideo.models.commons.CoUtils;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoFabriquant;
import net.ent.etrs.gestion_jeuvideo.models.dao.impl.DaoFactory;
import net.ent.etrs.gestion_jeuvideo.models.entities.Fabriquant;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeFabriquant;

import java.util.List;

public class FacadeFabriquantImpl extends GenericFacadeImpl<Fabriquant> implements IFacadeFabriquant {
    private final IDaoFabriquant daoFabriquant;

    protected FacadeFabriquantImpl() {
        super(DaoFactory.getDaoFabriquant());
        daoFabriquant = DaoFactory.getDaoFabriquant();
    }

    /**
     * Permet de sauvegarder un fabriquant.
     *
     * @param fabriquant le fabricant à sauvegarder
     * @return le fabricant sauvegarder
     * @throws BusinessException l'exception lancée
     */
    @Override
    public Fabriquant sauvegarderFabriquant(Fabriquant fabriquant) throws BusinessException {
        return super.save(fabriquant);
    }

    /**
     * Permet de récupérer tous les fabricants.
     *
     * @return la liste de tous les fabriquant
     * @throws BusinessException l'exception lancée
     */
    @Override
    public List<Fabriquant> recupererlesFabriquants() throws BusinessException {
        return CoUtils.iterableToList(super.findAll());
    }
}