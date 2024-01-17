package net.ent.etrs.projet.models.facades.interfaces;

import net.ent.etrs.projet.models.exceptions.BusinessException;

public interface IFacadeChargementFichier extends Runnable{
    void initialisation() throws BusinessException;
}
