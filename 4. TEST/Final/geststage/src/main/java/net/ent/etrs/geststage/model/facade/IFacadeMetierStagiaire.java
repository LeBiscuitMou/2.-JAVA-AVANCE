package net.ent.etrs.geststage.model.facade;

import net.ent.etrs.geststage.model.entities.Stage;
import net.ent.etrs.geststage.model.entities.Stagiaire;
import net.ent.etrs.geststage.model.entities.references.Matiere;
import net.ent.etrs.geststage.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierStagiaire {
    void creerStagiaire(Stagiaire stagiaire) throws BusinessException;

    void supprimerStagiaire(Stagiaire stagiaire) throws BusinessException;

    Set<Stagiaire> recupererTousLesStagiaires() throws BusinessException;
}