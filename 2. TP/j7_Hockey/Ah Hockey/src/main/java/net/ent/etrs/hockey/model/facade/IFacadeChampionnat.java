package net.ent.etrs.hockey.model.facade;

import net.ent.etrs.hockey.model.entities.Championnat;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeChampionnat {
    void saveChampionnat(Championnat championnat) throws BusinessException;
    void deleteChampionnat(Long idChampionnat) throws BusinessException;
    Set<Championnat> findAllChampionnats() throws BusinessException;
}
