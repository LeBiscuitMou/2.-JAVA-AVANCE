package net.ent.etrs.garage.model.facade;

import net.ent.etrs.garage.model.entities.Voiture;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierVoiture {
    Voiture creerVoiture(Voiture voiture) throws BusinessException;

    void supprimerVoiture(Voiture voiture) throws BusinessException;

    Set<Voiture> recupererToutesLesVoitures() throws BusinessException;
}