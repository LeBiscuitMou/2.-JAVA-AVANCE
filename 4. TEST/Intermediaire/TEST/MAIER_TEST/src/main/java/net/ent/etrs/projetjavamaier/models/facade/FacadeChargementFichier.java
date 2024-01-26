package net.ent.etrs.projetjavamaier.models.facade;

import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.io.IOException;

public interface FacadeChargementFichier  {
    void initialisation() throws BusinessException, IOException;
}
