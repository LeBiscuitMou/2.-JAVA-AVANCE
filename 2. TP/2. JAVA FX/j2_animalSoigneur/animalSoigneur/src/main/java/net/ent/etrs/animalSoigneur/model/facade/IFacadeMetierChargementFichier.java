package net.ent.etrs.animalSoigneur.model.facade;

import net.ent.etrs.animalSoigneur.model.facade.exceptions.BusinessException;

public interface IFacadeMetierChargementFichier {
    void initialisation() throws BusinessException;
}