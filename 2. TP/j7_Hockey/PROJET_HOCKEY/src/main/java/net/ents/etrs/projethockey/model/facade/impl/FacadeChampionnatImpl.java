package net.ents.etrs.projethockey.model.facade.impl;

import net.ents.etrs.projethockey.model.dao.DaoFactory;
import net.ents.etrs.projethockey.model.dao.IDaoChampionnat;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Championnat;
import net.ents.etrs.projethockey.model.entities.references.ConstantesMetier;
import net.ents.etrs.projethockey.model.facade.IFacadeChampionnat;
import net.ents.etrs.projethockey.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FacadeChampionnatImpl implements IFacadeChampionnat {
    IDaoChampionnat daoChampionnat;

    public FacadeChampionnatImpl() {
        this.daoChampionnat = DaoFactory.getDaoChampionnat();
    }

    @Override
    public void saveChampionnat(Championnat championnat) {
        try {
            this.daoChampionnat.save(championnat);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteChampionnat(Long idChampionnat) {

        try {
            Optional<Championnat> championnat = this.daoChampionnat.find(idChampionnat);


            if(championnat.isPresent()) {
                this.daoChampionnat.delete(championnat.get());
            }

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Championnat> findAllChampionnats() {
        try {
            return new HashSet<>(IterableUtils.toList(this.daoChampionnat.findAll()));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
