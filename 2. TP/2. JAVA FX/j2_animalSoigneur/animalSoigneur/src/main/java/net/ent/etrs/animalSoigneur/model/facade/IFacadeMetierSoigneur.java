package net.ent.etrs.animalSoigneur.model.facade;

import net.ent.etrs.animalSoigneur.model.entities.Animal;
import net.ent.etrs.animalSoigneur.model.entities.Soigneur;
import net.ent.etrs.animalSoigneur.model.facade.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;

public interface IFacadeMetierSoigneur {
    Soigneur creerSoigneur(Soigneur pSoigneur) throws BusinessException;

    void supprimerSoigneur(Soigneur pSoigneur) throws BusinessException;

    Soigneur modifierSoigneur(Soigneur pSoigneur);

    List<Soigneur> recupererTousLesSoigneurs() throws BusinessException;

    Optional<Soigneur> recupererSoigneurById(Long pId);
}