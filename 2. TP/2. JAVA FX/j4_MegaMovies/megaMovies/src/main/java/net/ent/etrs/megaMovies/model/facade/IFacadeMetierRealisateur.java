package net.ent.etrs.megaMovies.model.facade;

import net.ent.etrs.megaMovies.model.entities.Realisateur;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierRealisateur {
    Realisateur creerRealisateur(Realisateur entity) throws BusinessException;

    void supprimerRealisateur(Realisateur entity) throws BusinessException;

    Set<Realisateur> recupererTousLesRealisateur() throws BusinessException;
}