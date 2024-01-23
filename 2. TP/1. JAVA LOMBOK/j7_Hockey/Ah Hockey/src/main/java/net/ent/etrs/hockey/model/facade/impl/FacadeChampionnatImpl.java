package net.ent.etrs.hockey.model.facade.impl;

import net.ent.etrs.hockey.model.dao.IDaoChampionnat;
import net.ent.etrs.hockey.model.dao.impl.DaoFactory;
import net.ent.etrs.hockey.model.entities.Championnat;
import net.ent.etrs.hockey.model.exceptions.DaoException;
import net.ent.etrs.hockey.model.facade.IFacadeChampionnat;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FacadeChampionnatImpl implements IFacadeChampionnat {
    private final IDaoChampionnat daoChampionnat = DaoFactory.getDaoChampionnat();

    @Override
    public void saveChampionnat(Championnat championnat) throws BusinessException {
        try {
            daoChampionnat.save(championnat);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteChampionnat(Long idChampionnat) throws BusinessException {
        try {
            daoChampionnat.delete(idChampionnat);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Championnat> findAllChampionnats() throws BusinessException {
        try {
            return Collections.unmodifiableSet(new HashSet<>(IterableUtils.toList(daoChampionnat.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}