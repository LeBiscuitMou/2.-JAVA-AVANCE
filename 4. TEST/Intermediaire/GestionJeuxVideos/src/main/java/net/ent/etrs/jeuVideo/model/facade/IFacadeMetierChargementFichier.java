package net.ent.etrs.jeuVideo.model.facade;

import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;

public interface IFacadeMetierChargementFichier {
    /**
     * Permet d'initialisaer les données du logiciel
     */
    void initialisation() throws BusinessException;
}