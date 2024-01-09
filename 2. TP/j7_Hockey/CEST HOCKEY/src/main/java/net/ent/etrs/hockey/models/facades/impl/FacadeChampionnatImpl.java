package net.ent.etrs.hockey.models.facades.impl;

import net.ent.etrs.hockey.models.dao.DaoChampionnat;
import net.ent.etrs.hockey.models.dao.DaoFactory;
import net.ent.etrs.hockey.models.dao.exceptions.DaoException;
import net.ent.etrs.hockey.models.entities.Championnat;
import net.ent.etrs.hockey.models.facades.FacadeChampionnat;
import net.ent.etrs.hockey.models.facades.exception.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FacadeChampionnatImpl implements FacadeChampionnat {

    DaoChampionnat daoChampionnat;

    public FacadeChampionnatImpl() {
        this.daoChampionnat = DaoFactory.getDaoChampionnat();
    }

    @Override
    public void saveChampionnat(Championnat championnat) throws BusinessException {
        try {
            daoChampionnat.save(championnat);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de sauvegarder le championnat");
        }
    }

    @Override
    public void deleteChampionnat(Long idChampionnat) throws BusinessException {
        try {
            Optional<Championnat> championnat = this.daoChampionnat.find(idChampionnat);

            if (championnat.isPresent()) {
                this.daoChampionnat.delete(championnat.get());
            }
        } catch (DaoException e) {
            throw new BusinessException("Impossible de supprimer le championnat", e);
        }
    }

    @Override
    public Set<Championnat> findAllChampionnats() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoChampionnat.findAll()));
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les championnats", e);
        }
    }
}