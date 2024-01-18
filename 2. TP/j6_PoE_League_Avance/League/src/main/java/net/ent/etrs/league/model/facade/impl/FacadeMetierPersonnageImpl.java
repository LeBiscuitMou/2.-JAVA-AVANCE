package net.ent.etrs.league.model.facade.impl;

import lombok.*;
import net.ent.etrs.league.model.entities.Personnage;
import net.ent.etrs.league.model.facade.IFacadeMetierPersonnage;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

public class FacadeMetierPersonnageImpl implements IFacadeMetierPersonnage {
    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    @Override
    public void savePersonnage(Personnage personnage) {

    }

    /**
     * Permet de supprimer un personnage
     *
     * @param personnage le personnage à supprimer
     */
    @Override
    public void deletePersonnage(Personnage personnage) {

    }

    /**
     * Permet de récupérer tous les personnages
     *
     * @return les personnages de la base
     */
    @Override
    public Set<Personnage> findAllPersonnages() {
        return null;
    }
}