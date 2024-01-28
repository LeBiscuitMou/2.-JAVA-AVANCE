package net.ent.etrs.megaMovies.model.facade;

import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;

public interface IFacadeMetierChargementFichier {
    void initialisation() throws BusinessException;
}