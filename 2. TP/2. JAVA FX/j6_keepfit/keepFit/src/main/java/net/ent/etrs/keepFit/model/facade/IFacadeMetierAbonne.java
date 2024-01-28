package net.ent.etrs.keepFit.model.facade;

import net.ent.etrs.keepFit.model.entities.Abonne;
import net.ent.etrs.keepFit.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierAbonne {
    Abonne creerAbonne(Abonne entity) throws BusinessException;

    void supprimerAbonne(Abonne entity) throws BusinessException;

    Set<Abonne> recupererToutesLesAbonne() throws BusinessException;
}