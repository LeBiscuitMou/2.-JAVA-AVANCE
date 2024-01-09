package net.ent.etrs.hockey.models.facades;

import net.ent.etrs.hockey.models.entities.Championnat;
import net.ent.etrs.hockey.models.facades.exception.BusinessException;

import java.util.Set;

public interface FacadeChampionnat {
    void saveChampionnat(Championnat championnat) throws BusinessException;

    void deleteChampionnat(Long idChampionnat) throws BusinessException;

    Set<Championnat> findAllChampionnats() throws BusinessException;
}
