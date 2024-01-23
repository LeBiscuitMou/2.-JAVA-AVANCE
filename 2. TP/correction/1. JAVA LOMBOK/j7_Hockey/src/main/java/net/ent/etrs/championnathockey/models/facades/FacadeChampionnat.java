package net.ent.etrs.championnathockey.models.facades;

import net.ent.etrs.championnathockey.models.entities.Championnat;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;

import java.util.Set;

public interface FacadeChampionnat {
    /**
     * Permet de sauvegarder un championnat dans la base de donnée.
     * @param championnat le championnat à sauvegarder
     * @throws BusinessException s'il y a eu un problème avec la base de donnée
     */
    void saveChampionnat(Championnat championnat) throws BusinessException;
    /**
     * Permet de supprimer un championnat dans la base de donnée.
     * @param idChampionnat le championnat à supprimer
     * @throws BusinessException s'il y a eu un problème avec la base de donnée
     */
    void deleteChampionnat(Long idChampionnat) throws BusinessException;

    /**
     * Permet de récupérer tous les championnats dans la base de donnée.
     * @return un Set de tous les championnats
     * @throws BusinessException s'il y a eu un problème avec la base de donnée'
     */
    Set<Championnat> findAllChampionnats() throws BusinessException;


}
