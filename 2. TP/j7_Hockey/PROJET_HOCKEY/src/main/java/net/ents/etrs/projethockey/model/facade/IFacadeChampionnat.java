package net.ents.etrs.projethockey.model.facade;

import net.ents.etrs.projethockey.model.entities.Championnat;

import java.util.Set;

public interface IFacadeChampionnat {
    void saveChampionnat(Championnat championnat);
    void deleteChampionnat(Long idChampionnat);
    Set<Championnat> findAllChampionnats();
}
