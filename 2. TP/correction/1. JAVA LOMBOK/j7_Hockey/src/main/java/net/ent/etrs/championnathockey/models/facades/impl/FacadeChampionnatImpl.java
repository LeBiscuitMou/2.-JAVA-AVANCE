package net.ent.etrs.championnathockey.models.facades.impl;

import net.ent.etrs.championnathockey.models.daos.DaoChampionnat;
import net.ent.etrs.championnathockey.models.daos.DaoFactory;
import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Championnat;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.facades.FacadeChampionnat;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeChampionnatImpl implements FacadeChampionnat {

DaoChampionnat daoChampionnat;

    public FacadeChampionnatImpl() {
        daoChampionnat = DaoFactory.getDaoChampionnat();
    }

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
            return new HashSet<>(IterableUtils.toList(daoChampionnat.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }




}
