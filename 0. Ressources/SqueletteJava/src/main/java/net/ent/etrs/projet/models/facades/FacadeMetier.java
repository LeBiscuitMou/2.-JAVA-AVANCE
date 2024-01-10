package net.ent.etrs.projet.models.facades;

import net.ent.etrs.projet.models.entities.Exemple;
import net.ent.etrs.projet.models.exceptions.BusinessException;

import java.util.List;

public interface FacadeMetier {

    void SauvegarderExemple(Exemple ajoutExemple) throws BusinessException;

    List<Exemple> selectionnerTousLesExemple() throws BusinessException;

    void modifierExemple(Exemple modifieExemple) throws BusinessException;

    void supprimerExemple(Exemple supprimeExemple) throws BusinessException;
}
