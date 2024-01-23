package net.ent.etrs.cartes.models.facade.impl;

import net.ent.etrs.cartes.models.daos.DaoCaracteristique;
import net.ent.etrs.cartes.models.daos.DaoCarte;
import net.ent.etrs.cartes.models.daos.exceptions.DaoException;
import net.ent.etrs.cartes.models.daos.impl.DaoFactory;
import net.ent.etrs.cartes.models.entities.Caracteristique;
import net.ent.etrs.cartes.models.entities.Carte;
import net.ent.etrs.cartes.models.entities.references.Classe;
import net.ent.etrs.cartes.models.entities.references.TypeCarte;
import net.ent.etrs.cartes.models.facade.FacadeMetier;
import net.ent.etrs.cartes.models.facade.exception.BusinessException;

import java.time.Month;
import java.util.*;

public class FacadeMetierImpl implements FacadeMetier {
    DaoCarte daoCarte;
    DaoCaracteristique daoCaracteristique;

    public FacadeMetierImpl() {
        this.daoCarte = DaoFactory.fabriquerDaoCarte();
        this.daoCaracteristique = DaoFactory.fabriquerDaoCaracteristique();
    }

    @Override
    public void initialisation(List<String> stringList) {

    }

    @Override
    public Carte creerCarte(Carte carte) throws BusinessException {
        try {
            return this.daoCarte.save(carte);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Carte selectionnerCarte(String nom) {

    }

    @Override
    public void modifierCarte(Carte carte) throws BusinessException {
        try {
            daoCarte.delete(carte.getId());
            daoCarte.save(carte);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void supprimerCarte(Carte carte) {

    }

    @Override
    public Caracteristique creerCaracteristique(Caracteristique caracteristique) throws BusinessException {
        try {
            return this.daoCaracteristique.save(caracteristique);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Caracteristique selectionnerCaracteristique(String code) {
        return null;
    }

    @Override
    public void modifierCaracteristique(Caracteristique caracteristique) throws BusinessException {
        try {
            daoCaracteristique.delete(caracteristique.getId());
            daoCaracteristique.save(caracteristique);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void supprimerCaracteristique(Caracteristique caracteristique) throws BusinessException {
        try {
            daoCaracteristique.delete(caracteristique.getId());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Carte> recupererCartes(TypeCarte typeCarte) {
        return Collections.unmodifiableList(daoCarte.getCarteByType(typeCarte));
    }

    @Override
    public List<Carte> recupererCartes(Classe classe) {
        return Collections.unmodifiableList(daoCarte.getCarteByClasse(classe));
    }

    @Override
    public Map<Month, List<Carte>> recupererCartesAvecMois() {
        Map<Month, List<Carte>> map = new HashMap<>();
        return map;
    }
}